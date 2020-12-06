package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends MongoRepository<Country, String> {

    List<Country> findByNameLikeIgnoreCase(String name);
}
