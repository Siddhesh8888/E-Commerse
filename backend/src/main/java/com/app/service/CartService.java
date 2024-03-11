package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.request.AddItemRequest;

public interface CartService {

    Cart createCart(User user);
    String addCartItem(Long userId, AddItemRequest request) throws ProductException;
    Cart findUserCart(Long userId);
}
