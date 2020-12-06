package com.umbrella.demoSpringBoot.Service.mapper;

import com.umbrella.demoSpringBoot.Domain.City;
import com.umbrella.demoSpringBoot.Service.dto.CityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CityMapper extends GenericMapper<City, CityDTO> {

    @Mapping(source = "countryId", target = "country")
    City toEntity(CityDTO dto);

    @Mapping(source = "country.id", target = "countryId")
    CityDTO toDto(City entity);

    default City fromId(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
