package com.trustbank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trustbank.model.Statement;
import com.trustbank.model.Transaction;
import com.trustbank.model.User;
import com.trustbank.repository.StatementRepository;
import com.trustbank.repository.TransactionRepository;
import com.trustbank.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StatementRepository statementrepository;

	@Transactional
	public ResponseEntity<String> transferMoney(Long fromAccountNo, Long toAccountNo, double amount, String desc) {

		User fromUser = userRepository.findByAccountNo(fromAccountNo)
				.orElseThrow(() -> new RuntimeException("Sender not found"));
		User toUser = userRepository.findByAccountNo(toAccountNo)
				.orElseThrow(() -> new RuntimeException("Receiver not found"));

		if (fromUser.getAccountNo().equals(toUser.getAccountNo())) {
			return ResponseEntity.badRequest().body("Sender and receiver cannot be same");
		}

		if (fromUser.getBalance() < amount) {
			return ResponseEntity.badRequest().body("Insufficient balance");
		}

		// Update balances
		double fBal = fromUser.getBalance() - amount;
		double toBal = toUser.getBalance() + amount;
		fromUser.setBalance(fBal);
		toUser.setBalance(toBal);

		// Sender Statement (Debit)
		Statement fromStmt = new Statement();
		fromStmt.setDate(LocalDate.now());
		fromStmt.setWithdrawal(amount);
		fromStmt.setDrCr("Dr");
		fromStmt.setDescription("Transfer to " + toUser.getFirstname());
		fromStmt.setBalance(fBal);
		fromStmt.setUser(fromUser);
		statementrepository.save(fromStmt);

		
		// Receiver Statement (Credit)
		Statement toStmt = new Statement();
		toStmt.setDate(LocalDate.now());
		toStmt.setDeposit(amount);
		toStmt.setDrCr("Cr");
		toStmt.setDescription("Transfer from " + fromUser.getFirstname());
		toStmt.setBalance(toBal);
		toStmt.setUser(toUser);
		statementrepository.save(toStmt);

		Transaction tx = new Transaction();
		tx.setDate(LocalDate.now());
		tx.setDescription(desc);
		tx.setAmount(amount);
		tx.setFromUser(fromUser);
		tx.setToUser(toUser);
		transactionRepository.save(tx);

		return ResponseEntity.ok("Transfer successful. Transaction ID: " + tx.getTransactionId());
	}

	public List<Transaction> getTransaction(Long userId) {

		return transactionRepository.findTransactionsByFromUser(userId);
	}

	@Transactional
	public ResponseEntity<String> addBalance(Long accountNo, double amount) {
		User user = userRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		if (amount <= 0) {
			return ResponseEntity.badRequest().body("Amount must be positive");
		}

		double newBalance = user.getBalance() + amount;
		user.setBalance(newBalance);

		Statement stmt = new Statement();
		stmt.setDate(LocalDate.now());
		stmt.setDeposit(amount);
		stmt.setDrCr("Cr");
		stmt.setDescription("Balance Added");
		stmt.setBalance(newBalance);
		stmt.setUser(user);
		statementrepository.save(stmt);

		return ResponseEntity.ok("Balance added successfully. New balance: ₹" + newBalance);
	}

	@Transactional
	public ResponseEntity<String> withdrawBalance(Long accountNo, double amount) {
		User user = userRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		if (amount <= 0) {
			return ResponseEntity.badRequest().body("Amount must be positive");
		}

		if (user.getBalance() < amount) {
			return ResponseEntity.badRequest().body("Insufficient balance");
		}

		double newBalance = user.getBalance() - amount;
		user.setBalance(newBalance);

		Statement stmt = new Statement();
		stmt.setDate(LocalDate.now());
		stmt.setWithdrawal(amount);
		stmt.setDrCr("Dr");
		stmt.setDescription("Cash Withdrawal");
		stmt.setBalance(newBalance);
		stmt.setUser(user);
		statementrepository.save(stmt);

		return ResponseEntity.ok("Amount withdrawn successfully. New balance: ₹" + newBalance);
	}

}
