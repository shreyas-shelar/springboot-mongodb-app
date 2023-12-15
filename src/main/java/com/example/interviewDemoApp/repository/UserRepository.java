package com.example.interviewDemoApp.repository;

import com.example.interviewDemoApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>, PagingAndSortingRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findUserById(Long id);
}
