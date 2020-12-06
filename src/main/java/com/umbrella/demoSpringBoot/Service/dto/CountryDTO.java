package com.umbrella.demoSpringBoot.Service.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CountryDTO implements Serializable {


    private String id;

    @NotEmpty(message = "Country code  may not be empty")
    private String code;

    @NotEmpty(message = "Country name  may not be empty")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
