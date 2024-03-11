package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{

    @ManyToOne
    @JsonIgnore
    private Order order;
    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private int price;
    private int discountedPrice;

    private long userId;

    private LocalDateTime deliveryDate;
}
