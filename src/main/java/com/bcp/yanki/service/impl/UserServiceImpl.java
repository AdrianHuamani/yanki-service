package com.bcp.yanki.service.impl;

import com.bcp.yanki.common.ErrorMessage;
import com.bcp.yanki.domain.DebitCardResponse;
import com.bcp.yanki.domain.User;
import com.bcp.yanki.exception.FunctionalException;
import com.bcp.yanki.repository.DocumentTypeRepository;
import com.bcp.yanki.repository.UserRepository;
import com.bcp.yanki.service.UserService;
import com.bcp.yanki.web.contracts.CreateUserRequest;
import com.bcp.yanki.web.mapper.UserMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.MonoProcessor;
import io.reactivex.rxjava3.core.FlowableConverter;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final UserMapper userMapper;



    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:9042/v1").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

    @Override
    public Flux<DebitCardResponse> findByCustomerInternalCode(String customerInternalCode) {

        return this.webClient.get().uri("/debitCards/{customerInternalCode}", customerInternalCode)
                .retrieve()
                .bodyToFlux(DebitCardResponse.class);
    }

    @Override
    public Flowable<User> findAll() {
        log.debug("findAll executed");
        return userRepository.findAll();
    }



    @Override
    public Maybe<User> findById(String id) {
        log.debug("findById executed {}", id);
        return userRepository.findById(id);
    }

    @Override
    public Maybe<User> save(CreateUserRequest createUserRequest) {
        log.debug("create executed {}", createUserRequest);
        return Maybe.just(createUserRequest)
                .flatMapSingle(temporalCreateUserRequest -> documentTypeRepository.existsById(temporalCreateUserRequest.getDocumentType().getId())
                        .flatMap(existDocumentType -> Boolean.FALSE.equals(existDocumentType)
                                ? Single.error(new FunctionalException(ErrorMessage.DOCUMENT_TYPE_NOT_FOUND.getValue()))
                                : Single.just(temporalCreateUserRequest))
                        .flatMap(temporalCreateUserRequestTwo -> userRepository.existsByCellPhoneNumberOrCellPhoneImei(temporalCreateUserRequestTwo.getDocumentNumber(), temporalCreateUserRequestTwo.getCellPhoneImei())
                                .flatMap(cellPhoneIsRegister -> Boolean.TRUE.equals(cellPhoneIsRegister)
                                        ? Single.error(new FunctionalException(ErrorMessage.CELL_PHONE_IS_ALREADY_USED.getValue()))
                                        : Single.just(temporalCreateUserRequestTwo)))
                        .flatMap(temporalCreateUserRequestThree -> userRepository.existsByDocumentNumberAndDocumentType(temporalCreateUserRequestThree.getDocumentNumber(), temporalCreateUserRequestThree.getDocumentType())
                                .flatMap(userIsRegister -> Boolean.TRUE.equals(userIsRegister)
                                        ? Single.error(new FunctionalException(ErrorMessage.DUPLICATE_USER.getValue()))
                                        : Single.just(temporalCreateUserRequestThree)))
                        .flatMap(temporalCreateUserRequestFour -> documentNumberIsCorrectFormat(temporalCreateUserRequestFour.getDocumentNumber(), temporalCreateUserRequestFour.getDocumentType().getId())
                                .flatMap(documentNumberHasCorrectFormat -> Boolean.FALSE.equals(documentNumberHasCorrectFormat)
                                        ? Single.error(new FunctionalException(ErrorMessage.DOCUMENT_NUMBER_IS_INCORRECT_FORMAT.getValue()))
                                        : Single.just(temporalCreateUserRequestFour)))

                ).map(userMapper::createRequestToEntity)
                .map(this::setAuditableData)
                .flatMapSingle(userRepository::save);
    }

    public Single<Boolean> documentNumberIsCorrectFormat(String documentNumber, String documentTypeId) {
        return documentTypeRepository.findById(documentTypeId)
                .map(documentType -> documentType.getLength() == documentNumber.length())
                .toSingle();
    }

    public User setAuditableData(User user) {
        user.setCreationDateOnUtc(Instant.now());
        user.setUserCreationId(System.getProperty("user.name"));
        return user;
    }


}
