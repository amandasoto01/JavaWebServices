package com.rest.webservieces.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservieces.restfulwebservices.post.Post;
import com.rest.webservieces.restfulwebservices.postjpa.PostJ;
import com.rest.webservieces.restfulwebservices.postjpa.PostRepository;

@RestController
public class UserJPAResource {
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	//GET /users
	//retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	//GET /users/{id}
	//retrieveUser 
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		//find by id when does not exist return a proper object
		Optional<User> user = userRepository.findById(id);
		//System.out.println(userRepository.findById(id).isPresent());
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+ id);
		}
		
		//HATEOAS
		//when the retrieveUser is called return a link to get the details of all the users
		//"all-users", SERVER_PATH + "/users"
		
		EntityModel<User> model = new EntityModel<>(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	//CREATED
	//input -details of user 
	//output - Status created & return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		//CREATED best practice when a resource is created
		// /user/{id}   /user/savedUser.getId
		//take the current request uri and append the id
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	

	@GetMapping("/jpa/users/{id}/posts")
	public List<PostJ> retrieveAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id- "+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody PostJ post) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id- "+id);
		}
		
		User userFound = user.get();
		
		post.setUser(userFound);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
