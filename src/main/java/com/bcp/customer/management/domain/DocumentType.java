package com.bcp.customer.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "documentTypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DocumentType extends AuditableEntity{

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @Size(max=2)
    @Indexed(unique=true)
    private String code;

    @NotNull
    @Indexed(unique=true)
    private String description;

    @NotNull
    private Integer length;

    @NotNull
    private Boolean status;

}
