package com.bcp.customer.management.web.contracts;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DocumentTypeResponse {

    private String id;

    private String code;

    private String description;

    private Integer length;

    private Boolean status;
}
