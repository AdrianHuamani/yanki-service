package com.bcp.customer.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Transaction extends AuditableEntity{

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private BigDecimal amount;

    private String sourceAccountId;

    private String destinationAccountId;

}
