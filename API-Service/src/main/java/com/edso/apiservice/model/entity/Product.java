package com.edso.apiservice.model.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    private String description;

    private BigDecimal price;

    @Column(columnDefinition = "int default 0")
    private Integer quantity;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime updateAt;

    @PrePersist
    void onCreate(){
        this.setCreatedAt(OffsetDateTime.now());
    }

    private String createdBy;
}
