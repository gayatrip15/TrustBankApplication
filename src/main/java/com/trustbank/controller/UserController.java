package com.trustbank.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.trustbank.model.User;
import com.trustbank.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/save-user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/get-user/{userId}")
	public Optional<User> getAllUser(@PathVariable Long userId) {
		return userService.findById(userId);

	}

	@GetMapping("/user-list")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/delete1/{id}")
	public String deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);

	}

	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User updatedUserData) {
		User updatedUser = userService.updateUser(id, updatedUserData);
		return updatedUser;
	}

	@PostMapping("/password")
	public User login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();

		return userService.getUser(username, password);
	}

}
