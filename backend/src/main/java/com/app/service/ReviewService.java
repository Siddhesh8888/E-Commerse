package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Review;
import com.app.model.User;
import com.app.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewRequest reviewRequest, User user) throws ProductException;

    List<Review> getAllProductReviews(Long productId);
}
