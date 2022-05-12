package io.starseed.userservice;

import io.starseed.userservice.domain.Account;
import io.starseed.userservice.domain.Role;
import io.starseed.userservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AccountserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AccountService accountService){
		return  args -> {
			accountService.saveRole(new Role(null, "ROLE_USER"));
			accountService.saveRole(new Role(null, "ROLE_MANAGER"));
			accountService.saveRole(new Role(null, "ROLE_ADMIN"));
			accountService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			/*
			accountService.saveRole(new Role(null, "ROLE_USER", new ArrayList<>()));
			accountService.saveRole(new Role(null, "ROLE_MANAGER",new ArrayList<>()));
			accountService.saveRole(new Role(null, "ROLE_ADMIN", new ArrayList<>()));
			accountService.saveRole(new Role(null, "ROLE_SUPER_ADMIN", new ArrayList<>()));
			*/

			accountService.saveAccount(new Account(null, "John Travolta", "john", "1234", new ArrayList<>()));
			accountService.saveAccount(new Account(null, "Will Smith", "will", "1234", new ArrayList<>()));
			accountService.saveAccount(new Account(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			accountService.saveAccount(new Account(null, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));

			accountService.addRoleToAccount("john", "ROLE_USER");
			accountService.addRoleToAccount("john", "ROLE_MANAGER");
			accountService.addRoleToAccount("will", "ROLE_MANAGER");
			accountService.addRoleToAccount("jim", "ROLE_ADMIN");
			accountService.addRoleToAccount("arnold", "ROLE_SUPER_ADMIN");
			accountService.addRoleToAccount("arnold", "ROLE_ADMIN");
			accountService.addRoleToAccount("arnold", "ROLE_USER");
		};
	}
}
