package com.example.restservice.auth;

import com.example.restservice.user.entity.RoleEntity;
import com.example.restservice.user.entity.RoleEntityEntity;
import com.example.restservice.user.service.UserService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//-classpath /home/anh/Desktop/repo/respository/java/auth/target/classes
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();

	}
	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRoleEntity(new RoleEntity(null, "ROLE_USER"));
			userService.saveRoleEntity(new RoleEntity(null, "ROLE_SUPPER_USER"));
			userService.saveRoleEntity(new RoleEntity(null, "USER_MANAGE"));
			userService.saveRoleEntity(new RoleEntity(null, "USER_IT"));
			userService.saveRoleEntity(new RoleEntity(null, "USER_HR"));

			userService.saveUserEntity(new User(null, "Anh", "anh123", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Dat", "dat123", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Sang", "sang123", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Long", "long23", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "TanLy", "tanly123", "123456", new ArrayList<>()));

			userService.addRoleEntityToUser("anh123", "ROLE_USER");
			userService.addRoleEntityToUser("anh123", "USER_HR");
			userService.addRoleEntityToUser("dat123", "ROLE_SUPPER_USER");
			userService.addRoleEntityToUser("sang123", "USER_MANAGE");
			userService.addRoleEntityToUser("long23", "USER_IT");
			userService.addRoleEntityToUser("tanly123", "USER_HR");
		};
	}
}
