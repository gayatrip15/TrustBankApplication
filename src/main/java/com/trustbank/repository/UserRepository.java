package com.trustbank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trustbank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByAccountNo(Long accountNumber);

	User findByUsernameAndPassword(String username, String password);

}
