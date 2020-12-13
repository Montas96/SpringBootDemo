package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaTypeRepository extends MongoRepository<MediaType, String> {
}
