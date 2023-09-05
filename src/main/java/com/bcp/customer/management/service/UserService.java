package com.bcp.customer.management.service;

import com.bcp.customer.management.domain.DocumentType;
import com.bcp.customer.management.domain.User;
import com.bcp.customer.management.web.contracts.CreateUserRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface UserService {
    Flowable<User> findAll();
    Maybe<User> findById(String id);
    Maybe<User> save(CreateUserRequest user);
}
