package com.edso.apiservice.service;

import com.edso.apiservice.exception.InvalidException;
import com.edso.apiservice.model.entity.Product;
import com.edso.apiservice.model.request.ProductRequest;
import com.edso.apiservice.model.response.ListProductResponse;
import com.edso.apiservice.model.response.ProductResponse;
import com.edso.apiservice.repository.ProductRepository;
import com.edso.apiservice.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Optional<Product> getById(Long id) {
        LOGGER.info("Inside get Product by id of TransactionController");
        Optional<Product> product = productRepository.findById(id);
        productMapper.mapToProductResponse(product.get());
        return product;
    }

    @Override
    public ListProductResponse getListProduct(Integer pageIndex, Integer pageSize) {

        LOGGER.info("Inside get list Product of TransactionController");

        ListProductResponse response = new ListProductResponse();

        Pageable paging = PageRequest.of(pageIndex - 1, pageSize, Sort.by("id"));
        Page<Product> page = productRepository.findAll(paging);

        List<Product> products = page.getContent();

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(productMapper.mapToProductResponse(product));
        }

        response.setProductResponsesList(productResponses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPages(page.getTotalPages());
        response.setTotalItems((int) page.getTotalElements());
        return response;
    }

    @Override
    public ProductResponse createProduct(String user, ProductRequest productRequest) {
        LOGGER.info("Inside created Product of TransactionController");
        Product product = productMapper.mapToProduct(user, productRequest);

        Product productCreated = productRepository.save(product);

        return productMapper.mapToProductResponse(productCreated);
    }

    @Override
    public ProductResponse updateProductById(Long id, String user, ProductRequest productRequest) {
        LOGGER.info("Inside update Product by id of TransactionController");
        Product product = productRepository.getById(id);
        OffsetDateTime time = product.getCreatedAt();
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new InvalidException("Invalid mission has id = " + id);
        }
        productRequest.setId(id);
        product.setUpdateAt(OffsetDateTime.now());
        Product productConvert = productMapper.mapToProduct(user, productRequest);
        productConvert.setCreatedAt(time);
        Product productEdit = productRepository.save(productConvert);

        return productMapper.mapToProductResponse(productEdit);
    }

    @Override
    public boolean deleteProductById(Long id) {
        LOGGER.info("Inside delete Product of TransactionController");
        productRepository.deleteById(id);
        return true;
    }
}
