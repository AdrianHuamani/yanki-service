package com.bcp.customer.management.web.contracts;

import com.bcp.customer.management.domain.DocumentType;
import lombok.*;

import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreateUserRequest {

    @NotNull
    @NotBlank(message="name cannot be null or empty")
    private String name;

    @NotNull
    @NotBlank(message="lastName cannot be null or empty")
    private String lastName;

    @NotNull
    private DocumentType documentType;

    @NotNull
    @NotBlank(message="documentCode cannot be null or empty")
    private String documentNumber;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String cellPhoneNumber;

    @NotNull
    private String cellPhoneImei;

    @NotNull
    private @Email String email;

}
