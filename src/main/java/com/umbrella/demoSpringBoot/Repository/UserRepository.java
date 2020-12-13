package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findByCreationDateBetween(LocalDate fromDate,LocalDate toDate, Pageable pageable);
}
