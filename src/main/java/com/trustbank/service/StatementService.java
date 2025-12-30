package com.trustbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trustbank.model.Statement;
import com.trustbank.repository.StatementRepository;

@Service
public class StatementService {
	@Autowired
	StatementRepository statementrepository;

	public List<Statement> getAllStatements(Long userId) {

		return statementrepository.findStatementsByUserId(userId);
	}

}
