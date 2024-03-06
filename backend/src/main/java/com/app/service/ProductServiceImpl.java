package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.app.request.CreateProductRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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

    @Override
    public Product findProductById(Long id) throws ProductException {
        return productRepository.findById(id).orElseThrow(()->new ProductException("Invalid product id "+id));
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "product with id "+productId+" deleted successfully!!";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        Product persistProduct = findProductById(productId);

        if (product!=null)
        {
            persistProduct.setDiscountPercent(product.getDiscountPercent());
            persistProduct.setBrand(product.getBrand());
            persistProduct.setColor(product.getColor());
            persistProduct.setPrice(product.getPrice());
            persistProduct.setQuantity(product.getQuantity());
            persistProduct.setDiscountedPrice(product.getDiscountedPrice());
        }
        return productRepository.save(persistProduct);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes,
                                        int minPrice, int maxPrice, String sort, String stock, int minDiscount,
                                        int pageNumber, int pageSize) {

        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if (!products.isEmpty())
        {
            if(!colors.isEmpty())
            {
                products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).toList();
            }
        }
        if(stock!=null)
        {
            if (stock.equalsIgnoreCase("in_stock"))
            {
                products=products.stream().filter(p->p.getQuantity()>0).toList();
            }
            else if (stock.equalsIgnoreCase("out_of_stock")) {
            products=products.stream().filter(p->p.getQuantity()<=0).toList();
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(),products.size());

        List<Product> pageContent = products.subList(startIndex,endIndex);

        return new PageImpl<>(pageContent,pageable,products.size());
    }

}
