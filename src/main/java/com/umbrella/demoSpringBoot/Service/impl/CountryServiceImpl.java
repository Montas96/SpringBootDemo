package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Controller.Exception.CountryNotFoundException;
import com.umbrella.demoSpringBoot.Repository.CountryRepository;
import com.umbrella.demoSpringBoot.Service.CountryService;
import com.umbrella.demoSpringBoot.Service.dto.CountryDTO;
import com.umbrella.demoSpringBoot.Service.mapper.CountryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;


    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public Page<CountryDTO> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable).map(countryMapper::toDto);
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return countryMapper.toDto(countryRepository.findAll());
    }

    @Override
    public List<CountryDTO> findCountryByName(String nameQuery) {
        return countryMapper.toDto(countryRepository.findByNameLikeIgnoreCase(nameQuery));
    }

    @Override
    public CountryDTO getCountryById(String id) {
        return countryRepository.findById(id).map(countryMapper::toDto).orElseThrow(() -> new CountryNotFoundException());
    }
}
