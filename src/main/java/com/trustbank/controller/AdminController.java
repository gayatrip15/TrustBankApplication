package com.trustbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.trustbank.model.Admin;
import com.trustbank.service.AdminService;

@RestController

public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/adminlogin")
	public Admin login(@RequestBody Admin admin) {

		String username = admin.getAdminUserName();
		String pass = admin.getAdminPassword();

		return adminService.getadmin(username, pass);
	}

}
