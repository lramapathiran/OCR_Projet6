package com.lavanya.escalade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.AreaService;
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.UserService;

@Controller
public class SiteMainController {
	
	@Autowired 
	private SiteService siteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;
	

	
	@GetMapping("/createSite")
	public String showSiteForm(@RequestParam (value = "userId") int id, Model model) {
		
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
		
		Site site = new Site();
		site.setUserId(id);
		model.addAttribute("site", site);

		  
	    return "addSite";
	}
	
	
	@PostMapping("/saveSite")
	public String saveSite(@Valid @ModelAttribute ("site") Site site, BindingResult result, Model model) {
		
		
		int id = site.getUserId();
		if (result.hasErrors()) {
			User userConnected = userService.getUserById(id);
			model.addAttribute("user", userConnected);
	          return "addSite";
	    }
		
		siteService.save(site);
		
		int siteId = site.getId();
		
		
		return "redirect:/site/"+siteId;
//		return "redirect:/user?userId="+id;
	}
	
//	-----------------------Problème de url----------------------
	@GetMapping("/user/sites")
	public String showListOfSitesOfUser(@RequestParam (value = "userId") int id, User user, Model model) {
	   
	   user = userService.getUserById(id);
	   int userId = user.getId();
	   model.addAttribute("user", user);
	  

	   List<Site> listUserSites= siteService.getUserAllSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "userSites";
	}
	
	
	@GetMapping("/sites")
   	public String showSitesList(@RequestParam (value = "userId") int id, Model model) {
		
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
	   
		List<Site> listOfSites= siteService.getAllSites();
		model.addAttribute("listOfSites", listOfSites);
		
		return "sitesList";

    }
	
	@GetMapping(value = {"/site/{id}"})
	public String getSite(@PathVariable(name = "id") int id, Site site, Model model) {
		site = siteService.getSiteById(id);
		List<Area> listOfAreas= areaService.getAreasBySiteId(id);
		
		site.setAreas(listOfAreas);

		model.addAttribute("site", site);
		
		return "site.html";
	}
}
