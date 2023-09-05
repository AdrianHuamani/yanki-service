package com.bcp.customer.management.repository;

import com.bcp.customer.management.domain.DocumentType;
import com.bcp.customer.management.domain.User;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface UserRepository extends GenericRepository<User, String> {
    Single<Boolean> existsByDocumentNumberAndDocumentType(String documentNumber, DocumentType documentType);
    Single<Boolean> existsByCellPhoneNumberOrCellPhoneImei(String cellPhoneNumber, String cellPhoneImei);

}
