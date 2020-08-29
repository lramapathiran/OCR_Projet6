package com.lavanya.escalade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
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
import com.lavanya.escalade.model.Topo;
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
		
		site.setTagged(false);
		siteService.save(site);
		
		int siteId = site.getId();
		
		
		return "redirect:/site/"+siteId;
//		return "redirect:/user?userId="+id;
	}
	
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
   	public String showSitesListByPage(@RequestParam ("userId") int id, @RequestParam (value = "pageNumber") int currentPage, Model model) {
		
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
		
		Page<Site> page = siteService.getAllSites(currentPage);
		
		List<Site> sitesPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalSites = page.getTotalElements();
		
		model.addAttribute("sitesPage", sitesPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalSites", totalSites);
		
		
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
	
	@GetMapping("/showSites")
	public String showNextPagesOfSitesToVisitors(@RequestParam (value = "pageNumber") int currentPage, Model model) {
		
		Page<Site> page = siteService.getAllSites(currentPage);
		
		List<Site> sitesPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalSites = page.getTotalElements();
		
		model.addAttribute("sitesPage", sitesPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalSites", totalSites);
		
		
		return "sitesListForVisitors";
	}
	
	@PostMapping("/addTag")
	public String getTagOnSite(@Valid @ModelAttribute ("site") Site site, BindingResult result, int adminId, int currentPage, Model model) {
		
		if (result.hasErrors()) {
	          return "redirect:/sites?userId="+adminId+"&pageNumber="+currentPage;
	    }
		siteService.save(site);
		
		return "redirect:/sites?userId="+adminId+"&pageNumber="+currentPage;
	}
}
