package com.trustbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trustbank.model.Admin;
import com.trustbank.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Admin getadmin(String username, String pass) {

		return adminRepository.findByAdminUserNameAndAdminPassword(username, pass);
	}

}
