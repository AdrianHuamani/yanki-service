package com.bcp.yanki.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "transactionTypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TransactionType extends AuditableEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @Indexed(unique = true)
    private String description;

    @NotNull
    private Boolean status;

}
