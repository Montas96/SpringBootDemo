package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<CityDTO> getAllCities();

    Page<CityDTO> getAllCities(Pageable pageable);

    List<CityDTO> getAllCitiesByCountryId(String countryId);

    CityDTO getCityById(String id);

    List<CityDTO> findCityByName(String nameQuery);

    List<CityDTO> findCityByNameAndCountryId(String nameQuery, String countryId);
}
