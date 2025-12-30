package com.trustbank.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String firstname;
	private String lastname;
	private LocalDate dateOfBirth;
	private String gender;
	private String aadharCard;
	private String panNo;
	private String address;
	private String email;
	private String phoneNumber;

	private String accountType;
	private String bankName;
	@Column(name = "ifsccode")
	private String ifscCode;
	private Long accountNo;
	private double balance;
	private String username;
	private String password;

	@OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Transaction> sentTransactions;

	@OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Transaction> receivedTransactions;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Statement> receivedStatement;
}
