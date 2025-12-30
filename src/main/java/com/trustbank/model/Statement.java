package com.trustbank.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Entity
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statementId;
	private LocalDate date;
	private String description; // username
	private double withdrawal;
	private double deposit;
	private double balance;
	private String drCr;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
