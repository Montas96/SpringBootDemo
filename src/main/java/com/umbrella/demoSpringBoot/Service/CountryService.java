package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.CountryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    Page<CountryDTO> getAllCountries(Pageable pageable);

    List<CountryDTO> getAllCountries();

    List<CountryDTO> findCountryByName(String nameQuery);

    CountryDTO getCountryById(String id);
}
