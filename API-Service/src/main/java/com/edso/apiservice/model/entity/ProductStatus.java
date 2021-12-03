package com.edso.apiservice.model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductStatus {

    private Product product;
    private String status;
    private String message;
}
