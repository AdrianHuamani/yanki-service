package com.bcp.yanki.service.impl;

import com.bcp.yanki.domain.DocumentType;
import com.bcp.yanki.repository.DocumentTypeRepository;
import com.bcp.yanki.service.DocumentTypeService;
import com.bcp.yanki.web.mapper.DocumentTypeMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public Flowable<DocumentType> findAll() {
        log.debug("findAll executed");
        return documentTypeRepository.findAll();
    }

    @Override
    public Maybe<DocumentType> findById(String id) {
        log.debug("findById executed {}", id);
        return documentTypeRepository.findById(id);
    }

    @Override
    public Single<DocumentType> save(DocumentType documentType) {
        log.debug("create executed {}", documentType);
        documentType.setUserCreationId(System.getProperty("user.name"));
        documentType.setCreationDateOnUtc(Instant.now());
        return documentTypeRepository.save(documentType);
    }

}
