package com.community.student.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswersRepo extends MongoRepository<Answer,String>{
}
