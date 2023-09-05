package com.bcp.yanki.web.contracts;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreateDocumentTypeRequest {

    @NotBlank(message = "type cannot be null or empty")
    @Size(max = 2)
    @Indexed(unique = true)
    private String code;

    @NotBlank(message = "type cannot be null or empty")
    private String description;

    @Positive
    @NotNull
    private Integer length;

    @NotNull
    private Boolean status;

}
