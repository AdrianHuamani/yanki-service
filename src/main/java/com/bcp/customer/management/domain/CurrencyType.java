package com.bcp.customer.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "currencyTypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CurrencyType extends AuditableEntity{

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String symbol;

    @NotNull
    private BigDecimal salePrice;

    @NotNull
    private BigDecimal purchasePrice;

}
