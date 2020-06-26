package com.learning.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.jpa.entitiy.User;
import com.learning.jpa.entitiy.service.UserDAOService;
import com.learning.jpa.entitiy.service.UserRepository;

@Component
public class UserRepositoryServiceCommandLineRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryServiceCommandLineRunner.class);
	
	@Autowired
	private  UserRepository service;
	
	@Override
	public void run(String... args) throws Exception {
		//Save the user
		User user = new User("Jack", "Admin");
		log.info("New user is created: " + user);
		service.save(user);
		Optional<User> userWithIdOne =service.findById(1L);
		log.info("User is retrieved: " + userWithIdOne);
		
		List<User> users = service.findAll();
		log.info("All users: " + users);

	}

}
