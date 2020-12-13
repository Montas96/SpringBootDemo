package com.umbrella.demoSpringBoot.Service.dto;

import com.umbrella.demoSpringBoot.Domain.pojo.Coordinates;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private String address;
    private CountryDTO country;
    private CityDTO city;
    private String zipCode;
    private Coordinates coordinates;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
