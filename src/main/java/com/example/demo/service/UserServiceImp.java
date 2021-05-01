package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRegistrationDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserServiceImp implements UserService,UserDetailsService{

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	    @Override
	    public User findByEmail(String email){
	        return userRepository.findByEmail(email);
	    }
	    @Override
	    public User save(UserRegistrationDTO reg){
	        User user = new User();
	        user.setFirstName(reg.getFirstName());
	        user.setLastName(reg.getLastName());
	        user.setEmail(reg.getEmail());
	        user.setPassword(this.passwordEncoder.encode(reg.getPassword()));
	        
	        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
	        return userRepository.save(user);
	    }

	    @Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    	System.out.println("We are in loadUserByUserNameMethod");
	        User user = userRepository.findByEmail(email);
	        if (user == null){
	            throw new UsernameNotFoundException("Invalid username or password Please contact System Administrator.");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	                user.getPassword(),
	                mapRolesToAuthorities(user.getRoles()));
	    }

	    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	    }

		
	

}
