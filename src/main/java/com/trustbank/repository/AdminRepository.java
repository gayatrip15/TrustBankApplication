package com.trustbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trustbank.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByAdminUserNameAndAdminPassword(String adminUserName, String adminPassword);
}
