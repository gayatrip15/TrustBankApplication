package com.trustbank.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	private LocalDate date;
	private String description;
	private double amount;

	@ManyToOne
	@JoinColumn(name = "from_user_id")
	@JsonIgnoreProperties({ "transactionsSent", "transactionsReceived" })
	private User fromUser;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "transactionsSent", "transactionsReceived" })
	private User toUser;

}
