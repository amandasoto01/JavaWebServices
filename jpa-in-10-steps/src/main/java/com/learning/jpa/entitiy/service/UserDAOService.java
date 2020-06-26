package com.learning.jpa.entitiy.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.learning.jpa.entitiy.User;


@Repository 
//JPA implements @transactional
@Transactional //method will be involve with a transaction
public class UserDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		//Persistence context, only things that are involve in the persistence context will be handled by the entity manager
		entityManager.persist(user);
		return user.getId();
	}
}

/*
@Repository 
//JPA implements @transactional
@Transactional //method will be involve with a transaction
public class SomEntityDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(SomeEntity entity) {
		//Persistence context, only things that are involve in the persistence context will be handled by the entity manager
		entityManager.persist(entity);
		return user.getId();
	}
}*/


/*
 * Spring Data JPA
 * 
 * 
 * 
 */
