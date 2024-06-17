package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Product;
import com.app.model.Review;
import com.app.model.User;
import com.app.repository.ReviewRepository;
import com.app.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = productService.findProductById(reviewRequest.getProductId());

        Review review = new Review();
        review.setProduct(product);
        review.setReview(reviewRequest.getReview());
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllProductReviews(Long productId) {
        return reviewRepository.getProductReviews(productId);
    }
}
