package com.app.service;

import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException, UserException;
    CartItem isCartItemExists(Cart cart, Product product, String size, Long userId);
    void removeCartItem(Long userId, Long cartItemId)throws CartItemException, UserException;
    CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
