package com.app.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Product extends BaseEntity{
	
	private String title;
	private String description;
	private int price;
	private int discountPercent;
	private int discountedPrice;
	private int quantity;
	private String brand;
	private String color;
	
	@Embedded
	@ElementCollection
	@Column(name = "sizes")
	private Set<Size> sizes;
	
	private String imageUrl;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Rating ratings;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Review reviews;
	
	private int numRatings;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	private LocalDateTime createdAt;

}
