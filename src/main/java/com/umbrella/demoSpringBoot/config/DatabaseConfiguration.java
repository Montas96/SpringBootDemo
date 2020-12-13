package com.umbrella.demoSpringBoot.config;

import com.umbrella.demoSpringBoot.Domain.*;
import com.umbrella.demoSpringBoot.Domain.pojo.Coordinates;
import com.umbrella.demoSpringBoot.Repository.AddressRepository;
import com.umbrella.demoSpringBoot.Repository.CityRepository;
import com.umbrella.demoSpringBoot.Repository.CountryRepository;
import com.umbrella.demoSpringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Configuration
@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfiguration {


    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
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

        User user = new User();
        user.setUsername("Montassar");
        user.setLastName("Bouhjila");
        user.setFirstName("Montassar");
        user.setEmail("bouhjila.montassar@gmail.com");
        user.setCreationDate(LocalDateTime.now());
        user.setActivated(true);
        user.setPhoneNumber("51898905");
        Role role = new Role();
        role.setId("ROLE_USER");
        user.getRoles().add(role);

        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setZipCode("1006");
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(34.739822d);
        coordinates.setLongitude(10.7600196d);
        address.setCoordinates(coordinates);
        address = addressRepository.save(address);
        user.setAddress(address);
        user = userRepository.save(user);
    }

}
