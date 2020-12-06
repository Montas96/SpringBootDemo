package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.ERole;
import com.umbrella.demoSpringBoot.Domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}