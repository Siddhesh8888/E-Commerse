package com.app.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity{

    private String orderId;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails;

    private double totalPrice;
    private int totalDiscountedPrice;
    private int discount;
    private int totalItem;
    private String status;
    private LocalDateTime createdAt;
}
