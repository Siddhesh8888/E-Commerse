package com.app.service;

import com.app.exception.OrderException;
import com.app.model.*;
import com.app.repository.AddressRepository;
import com.app.repository.OrderItemRepository;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address savedAddress = addressRepository.save(shippingAddress);

        user.getAddress().add(savedAddress);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem : cart.getCartItems())
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(orderItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());
            orderItem.setSize(cartItem.getSize());
            orderItem.setDeliveryDate(LocalDateTime.now().plusDays(7));
            orderItem.setUserId(cartItem.getUserId());


            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Order createdOrder = new Order();
        createdOrder.setOrderItems(orderItems);
        createdOrder.setCreatedAt(LocalDateTime.now());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setDeliveryDate(LocalDateTime.now().plusDays(7));
        createdOrder.setUser(user);
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setStatus("Pending");
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentStatus("Pending");
        createdOrder.setPaymentDetails(paymentDetails);
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalItem(cart.getTotalItem());
        createdOrder.setShippingAddress(savedAddress);

        Order savedOrder = orderRepository.save(createdOrder);

        orderItems.stream().forEach(orderItem->{
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        });
        return savedOrder;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("PLACED");
        order.getPaymentDetails().setPaymentStatus("COMPLETED");
        return orderRepository.save(order);
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findById(id).orElseThrow(()->new OrderException("Invalid order id = "+id));
    }

    @Override
    public List<Order> getuserOrdersHistory(Long userId) {
        return orderRepository.findUserOrders(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) throws OrderException {
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }
}
