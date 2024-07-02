package com.app.controller;

import com.app.exception.ProductException;
import com.app.exception.UserException;
import com.app.model.User;
import com.app.request.AddItemRequest;
import com.app.response.ApiResponse;
import com.app.service.CartService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserCart(@RequestHeader("Authorization") String jwtToken) throws UserException {
        User user = userService.findUserProfileByJwt(jwtToken);
        return new ResponseEntity<>(cartService.findUserCart(user.getId()), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestHeader("Authorization") String jwtToken, @RequestBody AddItemRequest addItemRequest) throws ProductException, UserException {
        User user = userService.findUserProfileByJwt(jwtToken);
        String message = cartService.addCartItem(user.getId(), addItemRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
