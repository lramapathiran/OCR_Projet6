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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.UserService;

@Controller // This means that this class is a Controller
public class UserMainController {
  
	@Autowired
  private UserService userService;
  
  
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
	public String saveUser (@Valid @ModelAttribute("user") User user, BindingResult result) {
	  
	  if (result.hasErrors()) {
          return "redirect:/signup";
      }
      
	  user.setAdmin(false);
      userService.save(user);
//      model.addAttribute("users", userRepository.findAll());
      		
	
		return "redirect:/";
	}
  
   @GetMapping("/users")
   	public String showUsersList(Model model) {
	   List<User> usersList = userService.usersList();
	   model.addAttribute("listUsers", usersList);
	   return "usersList";
   }
}
