package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends MongoRepository<City, String> {

    Optional<City> findByCode(String code);

    List<City> findByNameLikeIgnoreCase(String name);

    List<City> findAllByCountry_Id(String countryId);

    List<City> findByNameLikeIgnoreCaseAndCountry_Id(String name, String countryId);
}
