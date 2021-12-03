package com.edso.apiservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updateAt;
}
