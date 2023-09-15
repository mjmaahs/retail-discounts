package com.retaildiscounts.repository;

import com.retaildiscounts.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    AppUser findByUsername(String username);

}
