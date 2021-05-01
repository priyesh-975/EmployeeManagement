package com.example.demo.service;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.User;
import com.example.demo.model.UserRegistrationDTO;

public interface UserService   {
	
	 User findByEmail(String email);

	 public User save(UserRegistrationDTO registration);

	
	
	

}
