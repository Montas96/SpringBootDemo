package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
