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
public class UserResponse {

    private String name;

    private String lastName;

    private String documentTypeDescription;

    private String documentNumber;

    private String cellPhoneNumber;

    private @Email String email;

}
