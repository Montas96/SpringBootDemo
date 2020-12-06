package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Controller.Exception.CityNotFoundException;
import com.umbrella.demoSpringBoot.Domain.City;
import com.umbrella.demoSpringBoot.Repository.CityRepository;
import com.umbrella.demoSpringBoot.Service.CityService;
import com.umbrella.demoSpringBoot.Service.dto.CityDTO;
import com.umbrella.demoSpringBoot.Service.mapper.CityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public List<CityDTO> getAllCities() {
        return cityMapper.toDto(cityRepository.findAll());
    }

    @Override
    public Page<CityDTO> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(cityMapper::toDto);
    }

    @Override
    public List<CityDTO> getAllCitiesByCountryId(String countryId) {
        return cityMapper.toDto(cityRepository.findAllByCountry_Id(countryId));
    }

    @Override
    public CityDTO getCityById(String id) {
        return cityRepository.findById(id).map(cityMapper::toDto).orElseThrow(() -> new CityNotFoundException());
    }

    @Override
    public List<CityDTO> findCityByName(String nameQuery) {
        List<City> cities = cityRepository.findByNameLikeIgnoreCase(nameQuery);
        if (CollectionUtils.isEmpty(cities)) {
            return Collections.emptyList();
        }
        return cityMapper.toDto(cities);
    }

    @Override
    public List<CityDTO> findCityByNameAndCountryId(String nameQuery, String countryId) {
        List<City> cities = cityRepository.findByNameLikeIgnoreCaseAndCountry_Id(nameQuery, countryId);
        if (CollectionUtils.isEmpty(cities)) {
            return Collections.emptyList();
        }
        return cityMapper.toDto(cities);
    }
}
