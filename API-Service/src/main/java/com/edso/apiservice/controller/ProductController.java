package com.edso.apiservice.controller;

import com.edso.apiservice.config.MessagingConfig;
import com.edso.apiservice.model.entity.CustomMessage;
import com.edso.apiservice.model.entity.Product;
import com.edso.apiservice.model.request.ProductRequest;
import com.edso.apiservice.model.response.ListProductResponse;
import com.edso.apiservice.model.response.ProductResponse;
import com.edso.apiservice.model.response.ResponseObject;
import com.edso.apiservice.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        Optional<Product> foundProduct = productService.getById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query product successfully", foundProduct)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find product with id " + id, "")
        );
    }

    @GetMapping
    public ListProductResponse getListProduct(@RequestParam(value = "pageIndex", defaultValue = "1",
            required = false) int pageIndex,
                                              @RequestParam(value = "pageSize", defaultValue = "10",
                                                      required = false) int pageSize) {
        return productService.getListProduct(pageIndex, pageSize);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createdMission(@RequestHeader("username") String user,
                                                          @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.createProduct(user, productRequest));
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<ProductResponse> updateMission(@PathVariable(value = "id") Long id,
                                                         @RequestHeader("username") String user,
                                                         @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.updateProductById(id, user, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean deleteProductById = productService.deleteProductById(id);
        if (deleteProductById) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find product to delete ", "")
        );
    }

    @PostMapping("/send")
    public String publishMessage(@RequestBody CustomMessage customMessage) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, customMessage.getMessage());
        return "Success !!";
    }
}
