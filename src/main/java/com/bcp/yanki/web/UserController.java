package com.bcp.yanki.web;

import com.bcp.yanki.domain.DebitCardResponse;
import com.bcp.yanki.domain.User;
import com.bcp.yanki.service.DocumentTypeService;
import com.bcp.yanki.service.UserService;
import com.bcp.yanki.web.contracts.CreateUserRequest;
import com.bcp.yanki.web.contracts.UserResponse;
import com.bcp.yanki.web.mapper.UserMapper;
import com.bcp.yanki.web.routes.ApiRoutes;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

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
                .map(userEntity -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(userEntity.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userEntity));
    }

    @GetMapping("debitCards/{customerInternalCode}")
    public Mono<ResponseEntity<Flux<DebitCardResponse>>> getAll(@PathVariable("customerInternalCode") String customerInternalCode){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(userService.findByCustomerInternalCode(customerInternalCode)));
    }

}
