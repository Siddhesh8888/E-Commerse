package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Category extends BaseEntity{
	
	@Size(max = 50)
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "parentCategory_id")
	private Category parentCategory;
	
	private int level;
	
	

}
