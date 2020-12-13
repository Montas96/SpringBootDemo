package com.umbrella.demoSpringBoot.Service.dto;

import java.io.Serializable;

public class MediaTypeDTO implements Serializable {

    private String id;
    private String code;
    private String label;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
