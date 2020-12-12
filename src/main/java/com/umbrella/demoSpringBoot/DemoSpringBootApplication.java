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
}
