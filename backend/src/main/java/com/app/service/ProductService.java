package com.app.service;

import com.app.model.Product;
import com.app.request.CreateProductRequest;

public interface ProductService {

    public Product createProduct(CreateProductRequest productRequest);
}
