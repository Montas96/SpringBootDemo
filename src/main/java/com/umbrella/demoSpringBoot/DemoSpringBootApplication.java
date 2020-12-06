package com.umbrella.demoSpringBoot;

import com.umbrella.demoSpringBoot.Domain.ERole;
import com.umbrella.demoSpringBoot.Domain.Role;
import com.umbrella.demoSpringBoot.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {
			Role role = new Role();
			role.setId("ROLE_USER");
			role.setName(ERole.ROLE_USER);
			roleRepository.save(role);

			role.setId("ROLE_ADMIN");
			role.setName(ERole.ROLE_ADMIN);
			roleRepository.save(role);

			role.setId("ROLE_MODERATOR");
			role.setName(ERole.ROLE_MODERATOR);
			roleRepository.save(role);

		};
	}
}
