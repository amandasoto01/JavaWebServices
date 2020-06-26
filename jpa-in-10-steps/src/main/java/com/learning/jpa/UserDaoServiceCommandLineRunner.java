package com.learning.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.jpa.entitiy.User;
import com.learning.jpa.entitiy.service.UserDAOService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	
	@Autowired
	private UserDAOService service;
	
	@Override
	public void run(String... args) throws Exception {
		//Save the user
		User user = new User("Jack", "Admin");
		Long insert = service.insert(user);
		log.info("New user is created: " + user);
	}

}
