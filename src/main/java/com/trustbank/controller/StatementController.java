package com.trustbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.trustbank.model.Statement;
import com.trustbank.service.StatementService;

@RestController
public class StatementController {

	@Autowired
	StatementService statementservice;

	@GetMapping("statements/{userId}")
	public List<Statement> getStatements(@PathVariable Long userId) {

		return statementservice.getAllStatements(userId);
	}

}
