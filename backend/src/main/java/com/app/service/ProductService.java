package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest productRequest);
    Product findProductById(Long id) throws ProductException;
    String deleteProduct(Long productId) throws ProductException;
    Product updateProduct(Long productId, Product product) throws ProductException;
    List<Product> findByCategory(String categoryName);
    Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, int minPrice, int maxPrice, String sort, String stock, int minDiscount, int pageNumber, int pageSize);

}
