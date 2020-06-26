package com.rest.webservieces.restfulwebservices.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostResource {
	
	@Autowired
	private PostDaoService service;
	
	@GetMapping("/users/{id}/post")
	public List<Post> retrieveAllPosts(@PathVariable int id){
		System.out.println("controller");
		System.out.println(id);
		return service.retrieveAllPosts(id);
	}
	
	@PostMapping("/users/{id}/posts")
	public Post createPost(@PathVariable int id, @RequestBody Post post) {
		return service.createPost(id, post);
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post detailsPost(@PathVariable int id, @PathVariable int post_id) {
		/*EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		return model;*/
		return service.detailsPost(id, post_id);
	}

	

}
