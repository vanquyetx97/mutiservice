package com.edso.apiservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProductResponse {

    List<ProductResponse> productResponsesList = new ArrayList<>();
    int page;
    int size;
    int totalPages;
    int totalItems;

}
