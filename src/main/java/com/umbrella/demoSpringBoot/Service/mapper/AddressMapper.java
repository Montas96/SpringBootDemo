package com.umbrella.demoSpringBoot.Service.mapper;

import com.umbrella.demoSpringBoot.Domain.Address;
import com.umbrella.demoSpringBoot.Service.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, CityMapper.class})
public interface AddressMapper extends GenericMapper<Address, AddressDTO> {

    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    Address toEntity(AddressDTO dto);

    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    AddressDTO toDto(Address entity);

    default Address fromId(String id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
