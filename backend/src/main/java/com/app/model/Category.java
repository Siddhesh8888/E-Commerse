package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{
	
	@Size(max = 50)
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "parentCategory_id")
	private Category parentCategory;
	
	private int level;
	
	

}
