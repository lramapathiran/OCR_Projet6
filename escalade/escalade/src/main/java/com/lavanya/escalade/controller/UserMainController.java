package com.lavanya.escalade.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.UserService;
import com.lavanya.escalade.service.SiteService;

@Controller // This means that this class is a Controller
public class UserMainController {
  
	@Autowired
    private UserService userService;
	
	@Autowired
	private SiteService siteService;
	
  
	@GetMapping("/")
	public String showHomePage() {
	  
		return "index";
	}

	

    @GetMapping("/signup")
	public String showSignUpForm (Model model) {
	  
    	User user = new User();
    	model.addAttribute("user", user);	  
    	return "addUser";
	}
  
    @PostMapping("/saveUser")
	public String saveUser (@ModelAttribute("user") @Valid User user,BindingResult result, @ModelAttribute("passwordFirstTry") String passwordFirstTry, Model model) {
	  
    	if (result.hasErrors()) {	  
    		return "addUser";
    	}
     
	 
    	if (user.getPassword().equals(passwordFirstTry)) {
    		user.setAdmin(false);
    		userService.save(user);
    	}
    	else {
    		String passwordErrormessage = "Les deux mots de passe doivent être identiques!";
    		model.addAttribute("passwordConfirmFail", passwordErrormessage);
    		return "addUser";
    	}
	 
      	return "redirect:/";
	}
  
    @GetMapping("/users")
   	public String showUsersList(@RequestParam (value = "userId") int id, Model model) {
	   
	   User userConnected = userService.getUserById(id);
	   model.addAttribute("user", userConnected);
	   
	   List<User> usersList = userService.getUsersList();
	   model.addAttribute("listUsers", usersList);
	   return "usersList";
    }
   
    @GetMapping("/user")
	public String showUserConnectedHomePage(@RequestParam (value = "userId") int id, User user, Model model) {
	   
	   user = userService.getUserById(id);
	   int userId = user.getId();
	   model.addAttribute("user", user);
	  

	   List<Site> listUserSites= siteService.getAllUserSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "user";
	}
   
   
    
}
