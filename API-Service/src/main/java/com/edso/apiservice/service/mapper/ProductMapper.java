package com.edso.apiservice.service.mapper;

import com.edso.apiservice.model.entity.Product;
import com.edso.apiservice.model.request.ProductRequest;
import com.edso.apiservice.model.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse mapToProductResponse(Product product) {
    ProductResponse productResponse = new ProductResponse();
    productResponse.setId(product.getId());
    productResponse.setName(product.getName());
    productResponse.setDescription(product.getDescription());
    productResponse.setPrice(product.getPrice());
    productResponse.setCreatedAt(product.getCreatedAt());
    productResponse.setUpdateAt(product.getUpdateAt());
    productResponse.setQuantity(product.getQuantity());
    productResponse.setCreatedBy(product.getCreatedBy());
    return productResponse;
    }

    public Product mapToProduct(String user, ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setCreatedAt(productRequest.getCreatedAt());
        product.setUpdateAt(productRequest.getUpdateAt());
        product.setCreatedBy(user);
        return product;
    }
}
