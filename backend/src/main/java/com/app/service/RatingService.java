package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Rating;
import com.app.model.User;
import com.app.request.RatingRequest;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingRequest ratingRequest, User user) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
