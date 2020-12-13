package com.umbrella.demoSpringBoot.Repository;

import com.umbrella.demoSpringBoot.Domain.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<Media, String> {
}
