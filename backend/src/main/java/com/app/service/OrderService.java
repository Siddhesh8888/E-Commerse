package com.app.service;

import com.app.exception.OrderException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, Address shippingAddress);
    Order placedOrder(Long orderId) throws OrderException;
    Order confirmedOrder(Long orderId) throws OrderException;
    Order shippedOrder(Long orderId) throws OrderException;
    Order deliveredOrder(Long orderId) throws OrderException;
    Order cancelOrder(Long orderId) throws OrderException;
    Order findOrderById(Long id) throws OrderException;
    List<Order> getuserOrdersHistory(Long userId);
    List<Order> getAllOrders();
    void deleteOrder(Long id) throws OrderException;


}
