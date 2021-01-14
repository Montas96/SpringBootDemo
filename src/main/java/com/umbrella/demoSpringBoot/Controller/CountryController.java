package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Service.CountryService;
import com.umbrella.demoSpringBoot.Service.dto.CountryDTO;
import com.umbrella.demoSpringBoot.Utils.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries-by-name")
    public ResponseEntity<List<CountryDTO>> findByName(@RequestParam String query){
        if(!StringUtils.hasText(query)){
            throw new RuntimeException("query can not be null or empty");
        }
        List<CountryDTO> countries = countryService.findCountryByName(query);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/countries/all")
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
            List<CountryDTO> countries = countryService.getAllCountries();
            return ResponseEntity.ok(countries);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries(Pageable pageable){
        Page<CountryDTO> countries = countryService.getAllCountries(pageable);
        HttpHeaders responseHeaders = PaginationUtils.generatePaginationHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),countries);
        return ResponseEntity.ok().headers(responseHeaders).body(countries.getContent());
    }
}
