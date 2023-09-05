package com.bcp.yanki.web;

import com.bcp.yanki.service.DocumentTypeService;
import com.bcp.yanki.web.contracts.CreateDocumentTypeRequest;
import com.bcp.yanki.web.contracts.DocumentTypeResponse;
import com.bcp.yanki.web.mapper.DocumentTypeMapper;
import com.bcp.yanki.web.routes.ApiRoutes;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.uriBaseDocumentType)
@Tag(name = "DOCUMENT TYPE", description = "document type controller")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    private final DocumentTypeMapper documentTypeMapper;

    @GetMapping

    public Maybe<ResponseEntity<Flowable<DocumentTypeResponse>>> findAll() {
        log.info("Find All Customer Type Controller executed");
        return documentTypeService.findAll().share()
                .isEmpty()
                .flatMapMaybe(isEmpty -> {
                    if (isEmpty) {
                        return Maybe.just(ResponseEntity.noContent().build());
                    } else {
                        return Maybe.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(documentTypeService.findAll().map(documentTypeMapper::entityToResponse)));
                    }
                });
    }

    @PostMapping
    public Single<ResponseEntity<DocumentTypeResponse>> save(@Valid @RequestBody CreateDocumentTypeRequest documentTypeModel,
                                                             final ServerHttpRequest req) {
        log.info("Save executed : " + documentTypeModel.toString());

        return documentTypeService.save(documentTypeMapper.createRequestToEntity(documentTypeModel))
                .map(documentTypeEntity -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(documentTypeEntity.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(documentTypeMapper.entityToResponse(documentTypeEntity)));
    }


}
