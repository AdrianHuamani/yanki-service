package com.bcp.yanki.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AuditableEntity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private DocumentType documentType;

    @NotNull
    private String documentNumber;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String cellPhoneNumber;

    @NotNull
    private String cellPhoneImei;

    @NotNull
    private @Email String email;

    //Lista de cuentas

}
