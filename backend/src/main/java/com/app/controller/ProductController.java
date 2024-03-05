package com.app.controller;

import com.app.request.CreateProductRequest;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    private ResponseEntity<?> addNewProduct(@RequestBody CreateProductRequest productRequest)
    {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }
}
