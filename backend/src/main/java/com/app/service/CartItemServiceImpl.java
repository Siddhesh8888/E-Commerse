package com.app.service;

import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if (user.getId().equals(userId))
        {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
            item.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExists(Cart cart, Product product, String size, Long userId) {

        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        if (user.getId().equals(userId))
        {
            cartItemRepository.delete(cartItem);
        }
        else
        {
            throw new UserException("You can't remove another users cart item !!");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        return cartItemRepository.findById(cartItemId).orElseThrow(()->new CartItemException("CartItem not found with id "+cartItemId));
    }
}
