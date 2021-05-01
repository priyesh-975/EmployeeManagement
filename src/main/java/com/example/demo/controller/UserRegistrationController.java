package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.model.User;
import com.example.demo.model.UserRegistrationDTO;

import com.example.demo.service.UserServiceImp;

@Controller	

@RequestMapping("/registration")
public class UserRegistrationController {
	
	
	@Autowired
    private UserServiceImp userService;
	
	
	    @ModelAttribute("user")
	    public UserRegistrationDTO userRegistrationDto() {
	        return new UserRegistrationDTO();
	    }
	    @GetMapping
	    public String showRegistrationForm(Model model) {
	        return "registration";
	    }
	    
	    @PostMapping
	    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDTO userDto,
	        BindingResult result) 
	    {

	        User existing = userService.findByEmail(userDto.getEmail());
	        if (existing != null) {
	            result.rejectValue("email", null, "There is already an account registered with that email");
	        }

	        if (result.hasErrors()) {
	            return "registration";
	        }

	        userService.save(userDto);
	        return "redirect:/registration?success";
	    }
}
