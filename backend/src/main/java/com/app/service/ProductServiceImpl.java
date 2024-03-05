package com.app.service;

import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.app.request.CreateProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product createProduct(CreateProductRequest productRequest) {

        Category topLevelCategory = categoryRepository.findByName(productRequest.getTopLevelCategory());
        if(topLevelCategory==null)
        {
            Category topLevel = new Category();
            topLevel.setName(productRequest.getTopLevelCategory());
            topLevel.setLevel(1);

            topLevelCategory = categoryRepository.save(topLevel);
        }

        Category secondLevelCategory = categoryRepository.findByNameAndParent(productRequest.getSecondLevelCategory(),topLevelCategory.getName());
        if(secondLevelCategory==null)
        {
            Category secondLevel = new Category();
            secondLevel.setName(productRequest.getSecondLevelCategory());
            secondLevel.setLevel(2);
            secondLevel.setParentCategory(topLevelCategory);

            secondLevelCategory = categoryRepository.save(secondLevel);
        }

        Category thirdLevelCategory = categoryRepository.findByNameAndParent(productRequest.getThirdLevelCategory(),secondLevelCategory.getName());
        if(thirdLevelCategory==null)
        {
            Category thirdLevel = new Category();
            thirdLevel.setName(productRequest.getThirdLevelCategory());
            thirdLevel.setLevel(3);
            thirdLevel.setParentCategory(secondLevelCategory);

            thirdLevelCategory = categoryRepository.save(thirdLevel);
        }

        Product product=modelMapper.map(productRequest,Product.class);
        product.setCategory(thirdLevelCategory);
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }
}
