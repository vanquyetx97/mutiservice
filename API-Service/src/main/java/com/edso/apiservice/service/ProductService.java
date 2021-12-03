package com.edso.apiservice.service;

import com.edso.apiservice.model.entity.Product;
import com.edso.apiservice.model.request.ProductRequest;
import com.edso.apiservice.model.response.ListProductResponse;
import com.edso.apiservice.model.response.ProductResponse;

import java.util.Optional;

public interface ProductService {

    Optional<Product> getById(Long id);

    ListProductResponse getListProduct(Integer pageIndex, Integer pageSize);

    ProductResponse createProduct(String user, ProductRequest productRequest);

    ProductResponse updateProductById(Long id, String user,  ProductRequest productRequest);

    boolean deleteProductById(Long id);
}