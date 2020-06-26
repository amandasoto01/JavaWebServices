package com.learning.jpa.entitiy.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.jpa.entitiy.User;

//acts as a shortcut to manage the entities, common abstraction 
public interface UserRepository extends JpaRepository<User, Long>{
	

}
