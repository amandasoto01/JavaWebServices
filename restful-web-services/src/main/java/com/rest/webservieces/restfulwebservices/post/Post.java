package com.rest.webservieces.restfulwebservices.post;

import java.util.Date;

public class Post {
	
	private Integer id;
	private int user;
	private String message;
	private Date date;
	
	public Post(int id, int user, String message, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.message = message;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
