package com.bcp.yanki.repository;

import com.bcp.yanki.domain.DocumentType;
import com.bcp.yanki.domain.User;
import io.reactivex.rxjava3.core.Single;

public interface UserRepository extends GenericRepository<User, String> {
    Single<Boolean> existsByDocumentNumberAndDocumentType(String documentNumber, DocumentType documentType);

    Single<Boolean> existsByCellPhoneNumberOrCellPhoneImei(String cellPhoneNumber, String cellPhoneImei);

}
