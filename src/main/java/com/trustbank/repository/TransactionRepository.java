package com.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.trustbank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// List<Transaction> findByFromUser_UserId(Long userId);

	@Query(value = "SELECT * FROM transaction WHERE from_user_id = :userId OR user_id = :userId ORDER BY date DESC", nativeQuery = true)
	List<Transaction> findTransactionsByFromUser(@Param("userId") Long userId);

}
