package com.rest.webservieces.restfulwebservices.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {
	
	private static List<Post> posts = new ArrayList<Post>();
	private int postsCount = 4;
	
	static {
		posts.add(new Post(1,1,"cazadores", new Date()));
		posts.add(new Post(2,2,"harry", new Date()));
		posts.add(new Post(3,3,"throne", new Date()));
		posts.add(new Post(4,1,"shiver", new Date()));
	}
	
	public List<Post> retrieveAllPosts(int id){
		List<Post> userPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if(post.getUser() == id) {
				userPosts.add(post);
			}
		}
		
		return userPosts;
	}
	
	public Post createPost (int id, Post post) {
		if( post.getId() == 0 ) {
			post.setId(++postsCount);
			post.setUser(id);
		}
		posts.add(post);
		return post;
	}
	
	public Post detailsPost(int userId, int postId) {
		
		for (Post post : posts) {
			if( post.getId() == postId && post.getUser() == userId) {
				return post;
			}
		}
		return null;
	}
}
