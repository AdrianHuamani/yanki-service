package com.bcp.customer.management.web.contracts;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UpdateUserRequest {

    @NotNull
    @NotBlank(message="name cannot be null or empty")
    private String name;

    @NotNull
    @NotBlank(message="documentCode cannot be null or empty")
    private String lastName;

    @NotNull
    private String documentTypeId;

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
