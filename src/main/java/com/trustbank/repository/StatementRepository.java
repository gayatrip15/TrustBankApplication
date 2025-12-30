package com.trustbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.trustbank.model.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

	@Query("SELECT s FROM Statement s WHERE s.user.userId = :userId")
	List<Statement> findStatementsByUserId(@Param("userId") Long userId);
	
  //SELECT * FROM statement WHERE user_id = 1;


}
