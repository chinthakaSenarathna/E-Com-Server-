package com.example.E_com;

import com.example.E_com.service.UserRoleService;
import com.example.E_com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EComApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EComApplication.class, args);
	}

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		userRoleService.initializerUserRoles();
		userService.initializeAdmin();
	}
}
