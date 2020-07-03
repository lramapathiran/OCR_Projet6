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
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.UserService;

@Controller
public class SiteMainController {
	
	@Autowired 
	private SiteService siteService;
	
	@Autowired
	private UserService userService;
	

	
	@GetMapping("/createSite")
	public String showSiteForm(@RequestParam (value = "userId") int id, Model model) {
		
		User userConnected = userService.getUserById(id);
		int userId = userConnected.getId();
		model.addAttribute("user", userConnected);
		
		Site site = new Site();
		site.setUserId(userId);
		model.addAttribute("site", site);

		  
	    return "addSite";
	}
	
	
	@PostMapping("/saveSite")
	public String saveSite(@Valid @ModelAttribute ("site") Site site, BindingResult result, Model model) {
		
		if (result.hasErrors()) {		 
	          return "createSite";
	    }
		
		siteService.save(site);
		
		int id = site.getUserId();
		
//		"redirect:/user/"+id;
		return "redirect:/user?userId="+id;
	}
	
//	-----------------------Problème de url----------------------
	@GetMapping("siites")
	public String showListOfSitesOfUser(@RequestParam (value = "userId") int id, User user, Model model) {
	   
	   user = userService.getUserById(id);
	   int userId = user.getId();
	   model.addAttribute("user", user);
	  

	   List<Site> listUserSites= siteService.getAllUserSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "userSites";
	}
	
	
	@GetMapping("/sites")
   	public String showUsersList(@RequestParam (value = "userId") int id, Model model) {
		
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
	   
		List<Site> listOfSites= siteService.getAllsites();
		model.addAttribute("listOfSites", listOfSites);
		
		return "sitesList";

    }
}
