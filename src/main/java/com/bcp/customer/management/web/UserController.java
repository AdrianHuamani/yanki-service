package com.bcp.customer.management.web;

import com.bcp.customer.management.domain.DocumentType;
import com.bcp.customer.management.domain.User;
import com.bcp.customer.management.service.DocumentTypeService;
import com.bcp.customer.management.service.UserService;
import com.bcp.customer.management.web.contracts.CreateUserRequest;
import com.bcp.customer.management.web.contracts.UserResponse;
import com.bcp.customer.management.web.mapper.UserMapper;
import com.bcp.customer.management.web.routes.ApiRoutes;
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
import java.time.Instant;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.uriBaseUser)
@Tag(name = "User", description = "user controller")
public class UserController {


    private final UserService userService;
    private final DocumentTypeService documentTypeService;
    private final UserMapper userMapper;

    @GetMapping
    public Maybe<ResponseEntity<Flowable<UserResponse>>> findAll() {
        log.info("Find All Customer Type Controller executed");
        return userService.findAll().share()
                .isEmpty()
                .flatMapMaybe(isEmpty -> {
                    if (isEmpty) {
                        return Maybe.just(ResponseEntity.noContent().build());
                    } else {
                        return Maybe.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(userService.findAll().map(userMapper::entityToResponse)));
                    }
                });
    }


    @PostMapping
    public Maybe<ResponseEntity<User>> save(@Valid @RequestBody CreateUserRequest createUserRequest,
                                                     final ServerHttpRequest req) {
        log.info("Save executed : " + createUserRequest.toString());
        return userService.save(createUserRequest)
                        .map(userEntity-> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(userEntity.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(userEntity));
    }



}
