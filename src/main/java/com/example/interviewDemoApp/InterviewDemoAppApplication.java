package com.example.interviewDemoApp;

import com.example.interviewDemoApp.repository.UserRepository;
import com.example.interviewDemoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class InterviewDemoAppApplication implements CommandLineRunner {


	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(InterviewDemoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
	}
}
