package com.example.MailService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestEntity {
    private String type;
    private String msg;
}
