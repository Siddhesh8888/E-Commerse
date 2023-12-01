package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
	
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private int zipcode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String mobile;

}
