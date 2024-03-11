package com.app.service;

import com.app.exception.OrderException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;

public interface OrderService {

    Order createOrder(User user, Address shippingAddress);
    Order findOrderById(Long id) throws OrderException;
}
