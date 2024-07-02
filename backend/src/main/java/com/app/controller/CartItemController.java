package com.app.controller;

import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.model.CartItem;
import com.app.model.User;
import com.app.response.ApiResponse;
import com.app.service.CartItemService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart_items")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@RequestHeader("Authorization") String jwtToken, @PathVariable Long cartItemId) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwtToken);
        cartItemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Item removed from cart!!");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestHeader("Authorization") String jwtToken, @PathVariable Long cartItemId, @RequestBody CartItem cartItem) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwtToken);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
    }

}
