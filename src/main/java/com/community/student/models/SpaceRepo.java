package com.community.student.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpaceRepo extends MongoRepository<Space,String>{
    Space findByTitle(String title);
}
