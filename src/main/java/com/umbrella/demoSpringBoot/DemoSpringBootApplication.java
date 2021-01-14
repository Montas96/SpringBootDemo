package com.umbrella.demoSpringBoot;

import com.umbrella.demoSpringBoot.Domain.ERole;
import com.umbrella.demoSpringBoot.Domain.Role;
import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Repository.RoleRepository;
import com.umbrella.demoSpringBoot.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {

		return args -> {
			Role role = new Role();
			role.setId("ROLE_USER");
			role.setName(ERole.ROLE_USER);
			roleRepository.save(role);

			role = new Role();
			role.setId("ROLE_MODERATOR");
			role.setName(ERole.ROLE_MODERATOR);
			roleRepository.save(role);

			role = new Role();
			role.setId("ROLE_ADMIN");
			role.setName(ERole.ROLE_ADMIN);
			roleRepository.save(role);

		};
	}
}
