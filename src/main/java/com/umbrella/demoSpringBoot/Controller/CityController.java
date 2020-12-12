package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Controller.Exception.IdNotFoundException;
import com.umbrella.demoSpringBoot.Service.CityService;
import com.umbrella.demoSpringBoot.Service.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<Page<CityDTO>> getAllCities(Pageable pageable) {
        Page<CityDTO> cities = cityService.getAllCities(pageable);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable String id) {
        if (!StringUtils.hasText(id)) {
            throw new IdNotFoundException();
        }
        CityDTO city = cityService.getCityById(id);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/cities-by-country/{countryId}")
    public ResponseEntity<List<CityDTO>> getCityByCountry(@PathVariable String countryId) {
        if (!StringUtils.hasText(countryId)) {
            throw new RuntimeException("countryId can not be null");
        }
        List<CityDTO> city = cityService.getAllCitiesByCountryId(countryId);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/cities-by-name/{nameQuery}")
    public ResponseEntity<List<CityDTO>> getCityByName(@PathVariable String nameQuery) {
        if (!StringUtils.hasText(nameQuery)) {
            throw new RuntimeException("query can not be null");
        }
        List<CityDTO> cities = cityService.findCityByName(nameQuery);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/cities/country/{countryId}/{query}")
    public ResponseEntity<List<CityDTO>> getCityByCountryIdAndByName(@PathVariable String countryId, @PathVariable String query) {
        if (!StringUtils.hasText(countryId)) {
            throw new RuntimeException("countryId can not be null");
        }
        if (!StringUtils.hasText(query)) {
            throw new RuntimeException("query can not be null");
        }
        List<CityDTO> cities = cityService.findCityByNameAndCountryId(countryId, query);
        return ResponseEntity.ok(cities);
    }
}
