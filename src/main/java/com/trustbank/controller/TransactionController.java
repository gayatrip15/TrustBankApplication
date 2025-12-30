package com.trustbank.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trustbank.model.Transaction;

import com.trustbank.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transfer")
	public ResponseEntity<String> transferAmount(@RequestBody Map<String, Object> payload) {

		Long fromAccountNo = Long.valueOf(payload.get("fromUser").toString());
		Long toAccountNo = Long.valueOf(payload.get("toUser").toString());
		double amount = Double.parseDouble(payload.get("amount").toString());
		String description = (String) payload.get("description");

		return transactionService.transferMoney(fromAccountNo, toAccountNo, amount, description);
	}

	@GetMapping("/get-transactions/{userId}")
	public List<Transaction> getTransacton(@PathVariable Long userId) {
		List<Transaction> getTransacton = transactionService.getTransaction(userId);
		return getTransacton;
	}

	@PostMapping("/add-balance")
	public ResponseEntity<String> addBalance(@RequestBody Map<String, Object> payload) {
		Long accountNo = Long.valueOf(payload.get("accountNo").toString());
		double amount = Double.parseDouble(payload.get("amount").toString());

		return transactionService.addBalance(accountNo, amount);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdrawBalance(@RequestBody Map<String, Object> payload) {
		Long accountNo = Long.valueOf(payload.get("accountNo").toString());
		double amount = Double.parseDouble(payload.get("amount").toString());

		return transactionService.withdrawBalance(accountNo, amount);
	}
}
