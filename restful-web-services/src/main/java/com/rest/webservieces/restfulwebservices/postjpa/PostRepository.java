package com.rest.webservieces.restfulwebservices.postjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservieces.restfulwebservices.post.Post;

@Repository
public interface PostRepository extends JpaRepository<PostJ, Integer>{

}
