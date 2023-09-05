package com.bcp.customer.management.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

@NoRepositoryBean
public interface GenericRepository<T,ID> extends RxJava3CrudRepository<T, ID> {
}
