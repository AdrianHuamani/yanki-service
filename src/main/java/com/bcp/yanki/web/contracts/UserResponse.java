package com.bcp.yanki.web.contracts;

import lombok.*;

import javax.validation.constraints.Email;

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
