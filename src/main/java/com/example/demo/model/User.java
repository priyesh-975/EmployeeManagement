package com.example.demo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstName;
	 private String lastName;
	   private String email;
	   private String password;
	   private String date;
	   
	   
	   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(
	        name = "users_roles",
	        joinColumns = @JoinColumn(
	            name = "user_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(
	            name = "role_id", referencedColumnName = "id"))
	    private Collection < Role > roles;
	
	   
	
   public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
	
	public User() {
		
	}
	
	
	 public User(String firstName, String lastName, String email, String password) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	    }

	    public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	        this.roles = roles;
	    }

  

}
