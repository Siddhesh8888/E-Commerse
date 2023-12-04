package com.app.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {
	
	private String cardholderName;
	private String cardNumber;
	private LocalDate expirationDate;
	private String cvv;

}
