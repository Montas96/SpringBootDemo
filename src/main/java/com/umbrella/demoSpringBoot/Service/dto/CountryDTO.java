package com.umbrella.demoSpringBoot.Service.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CountryDTO implements Serializable {


    private String id;

    @NotEmpty(message = "Country code  may not be empty")
    private String isoCode;

    @NotEmpty(message = "Country name  may not be empty")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
