package com.rest.webservieces.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.rest.webservieces.restfulwebservices.postjpa.PostJ;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have at least 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birthdate should be in the past")
	private Date birthdate;
	
	@OneToMany(mappedBy="user")
	private List<PostJ> posts;
	
	
	public List<PostJ> getPosts() {
		return posts;
	}

	public void setPosts(List<PostJ> posts) {
		this.posts = posts;
	}

	protected User() {
		
	}
	
	public User(Integer id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	
	
}
