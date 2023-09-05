package com.bcp.customer.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditableEntity {
    @NotNull
    private Instant creationDateOnUtc;
    private Instant modifiedDateOnUtc;
    @NotNull
    private String userCreationId;
    private String userUpdateId;
}
