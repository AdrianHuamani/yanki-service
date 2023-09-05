package com.bcp.customer.management.common;

import io.reactivex.rxjava3.core.Single;

public enum ErrorMessage {
    DOCUMENT_TYPE_NOT_FOUND("El tipo de documento seleccionado no existe o no está disponible."),
    CELL_PHONE_IS_ALREADY_USED("Es posible que su teléfono ya se encuentre afiliado a una cuenta Yanki."),
    DUPLICATE_USER("El usuario ya se encuentra registrado."),
    DOCUMENT_NUMBER_IS_INCORRECT_FORMAT("El número de documento ingresado no cumple el formato para el tipo de documento ingresado.");

    private String value;
    ErrorMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
