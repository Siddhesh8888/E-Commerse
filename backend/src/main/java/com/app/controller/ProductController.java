package com.app.controller;

import com.app.exception.ProductException;
import com.app.model.Product;
import com.app.request.CreateProductRequest;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    private ResponseEntity<?> addNewProduct(@RequestBody CreateProductRequest productRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @GetMapping
    private ResponseEntity<Page<Product>> getAllFilteredProducts(@RequestParam String category, @RequestParam List<String> color,
                                                     @RequestParam List<String> size, @RequestParam int minPrice,
                                                     @RequestParam int maxPrice, @RequestParam int minDiscount,
                                                     @RequestParam String sort, @RequestParam String stock,
                                                     @RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Product> allProducts = productService.getAllProducts(category, color, size, minPrice, maxPrice, sort, stock, minDiscount, pageNumber, pageSize);
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<?> getProduct(@PathVariable("id") Long productId) throws ProductException {
        return ResponseEntity.ok(productService.findProductById(productId));
    }
    @PutMapping("/id/{id}")
    private  ResponseEntity<?> updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductException {
        return ResponseEntity.ok(productService.updateProduct(id,product));
    }

    @DeleteMapping("/{id}")
    private  ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) throws ProductException {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/category")
    private  ResponseEntity<?> getProductByCategory(@RequestParam("category") String category) {
        List<Product> products = productService.findByCategory(category);

        if (products.isEmpty())
            return ResponseEntity.ok("No record available with category "+category);
        return ResponseEntity.ok(products);
    }
}
