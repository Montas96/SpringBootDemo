package com.umbrella.demoSpringBoot.Domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("addresses")
public class Address implements Serializable {

    private String address;


}
