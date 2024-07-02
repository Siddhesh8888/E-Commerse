package com.app.controller;

import com.app.exception.OrderException;
import com.app.exception.UserException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;
import com.app.service.OrderService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestHeader("Authorization") String jwtToken, @RequestBody Address shippingAddress) throws UserException {
        User user = userService.findUserProfileByJwt(jwtToken);
        return new ResponseEntity<>(orderService.createOrder(user, shippingAddress), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> usersOrderhistory(@RequestHeader("Authorization") String jwtToken) throws UserException {
        User user = userService.findUserProfileByJwt(jwtToken);
        return new ResponseEntity<>(orderService.getuserOrdersHistory(user.getId()),HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) throws UserException, OrderException {
        return new ResponseEntity<>(orderService.findOrderById(orderId),HttpStatus.OK);
    }
}
