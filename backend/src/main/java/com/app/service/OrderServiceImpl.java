package com.app.service;

import com.app.exception.OrderException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;
import com.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findById(id).orElseThrow(()->new OrderException("Invalid order id = "+id));
    }
}
