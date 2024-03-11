package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity{

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private int price;
    private int discountedPrice;
    private long userId;
}
