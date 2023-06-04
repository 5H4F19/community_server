package com.community.student.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String>{
    User findByUserId(String userId);
}
