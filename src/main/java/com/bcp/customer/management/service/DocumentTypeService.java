package com.bcp.customer.management.service;

import com.bcp.customer.management.domain.DocumentType;
import io.reactivex.rxjava3.core.*;

public interface DocumentTypeService{
    Flowable<DocumentType> findAll();
    Maybe<DocumentType> findById(String id);
    Single<DocumentType> save(DocumentType documentType);
}
