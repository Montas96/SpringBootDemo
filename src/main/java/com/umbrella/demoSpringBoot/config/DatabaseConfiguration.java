package com.umbrella.demoSpringBoot.config;

import com.umbrella.demoSpringBoot.Domain.City;
import com.umbrella.demoSpringBoot.Domain.Country;
import com.umbrella.demoSpringBoot.Repository.CityRepository;
import com.umbrella.demoSpringBoot.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

//@Configuration
//@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfiguration {


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    //@PostConstruct
    public void initializer() {
        Country country = new Country();
        country.setId("TUNISIA");
        country.setName("Tunisia");
        country.setIsoCode("TN");
        country = countryRepository.save(country);

        City city = new City();
        city.setId("TUNIS");
        city.setCode("TUNIS");
        city.setName("Tunis");
        city.setCountry(country);
        cityRepository.save(city);
    }

}
