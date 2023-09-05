package com.bcp.yanki.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "accounts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Account extends AuditableEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    private BigDecimal availableBalance;

    @NotNull
    private BigDecimal countableBalance;

    @NotNull
    private CurrencyType currencyType;

    private List<Transaction> transactions;

}
