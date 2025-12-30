package com.trustbank.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trustbank.model.User;
import com.trustbank.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {

		user.setBankName("Trust Bank");
		user.setIfscCode("TRUST0001");

		Random random = new Random();
		boolean checkUser = true;

		while (checkUser) {

			// Long accountNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);

			String bankPrefix = "201021";
			int lastSix = 100000 + random.nextInt(900000);
			String accountStr = bankPrefix + String.format("%06d", lastSix);
			long accountNumber = Long.parseLong(accountStr);

			Optional<User> availableUser = userRepository.findByAccountNo(accountNumber);
			if (availableUser.isEmpty()) {
				user.setAccountNo(accountNumber);
				checkUser = false;
			}
		}

		return userRepository.save(user);
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "user deleted successfully";
	}

	public User updateUser(Long id, User userdatafromui) {   // update data from ui

		User userdatafromdb = userRepository.findById(id).get();

		userdatafromdb.setFirstname(userdatafromui.getFirstname());
		userdatafromdb.setLastname(userdatafromui.getLastname());

		userdatafromdb.setDateOfBirth(userdatafromui.getDateOfBirth());

		userdatafromdb.setAadharCard(userdatafromui.getAadharCard());
		userdatafromdb.setPanNo(userdatafromui.getPanNo());
		userdatafromdb.setAddress(userdatafromui.getAddress());
		userdatafromdb.setEmail(userdatafromui.getEmail());
		userdatafromdb.setPhoneNumber(userdatafromui.getPhoneNumber());

		return userRepository.save(userdatafromdb);
	}

	public User getUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

}
