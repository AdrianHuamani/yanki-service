package com.bcp.yanki.service;

import com.bcp.yanki.domain.DocumentType;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface DocumentTypeService {
    Flowable<DocumentType> findAll();

    Maybe<DocumentType> findById(String id);

    Single<DocumentType> save(DocumentType documentType);
}
