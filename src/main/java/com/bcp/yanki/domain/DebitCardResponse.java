package com.bcp.yanki.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DebitCardResponse {

    @Id
    private String cardNumber;

    private String customerInternalCode;

    private LocalDate expiryDate;

    private Integer cvv;

    private String cardType;

    private String status;

    private List<Account> accounts;

}
