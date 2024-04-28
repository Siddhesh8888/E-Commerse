package com.app.service;

import com.app.exception.ProductException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CartRepository;
import com.app.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(request.getProductId());

        CartItem isPresent = cartItemService.isCartItemExists(cart,product, request.getSize(),userId);
        if (isPresent==null)
        {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setSize(request.getSize());
            cartItem.setPrice(product.getPrice()* request.getQuantity());
            cartItem.setDiscountedPrice(product.getDiscountedPrice()* request.getQuantity());
            cartItem.setUserId(userId);
            cartItem.setCart(cart);
            CartItem createdCartItem=cartItemService.createCartItem(cartItem);

            cart.getCartItems().add(createdCartItem);
        }
        return "Item add to cart!!";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalQuantity=0;

        for (CartItem cartItem: cart.getCartItems())
        {
            totalPrice+=cartItem.getPrice();
            totalDiscountedPrice+=cartItem.getDiscountedPrice();
            totalQuantity += cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalQuantity);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
