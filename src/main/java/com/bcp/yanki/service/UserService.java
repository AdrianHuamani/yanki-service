package com.bcp.yanki.service;

import com.bcp.yanki.domain.User;
import com.bcp.yanki.web.contracts.CreateUserRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface UserService {
    Flowable<User> findAll();

    Maybe<User> findById(String id);

    Maybe<User> save(CreateUserRequest user);
}
