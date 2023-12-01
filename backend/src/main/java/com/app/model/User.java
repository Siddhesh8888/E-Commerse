package com.app.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private String mobile;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private Address address;
	
	@Embedded
	@ElementCollection
	@CollectionTable(name = "pay_info", joinColumns = @JoinColumn(name="user_id"))
	private PaymentInformation payment;
	
	@OneToMany
	@JsonIgnore
	private Rating ratings;
	
	@OneToMany
	@JsonIgnore
	private Review reviews;
	private LocalDateTime date;

}
