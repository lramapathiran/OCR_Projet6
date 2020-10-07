package com.lavanya.escalade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.lavanya.escalade.service.MyUserDetails;
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.TopoService;
import com.lavanya.escalade.service.UserService;

@Controller
public class SiteMainController {
	
	@Autowired 
	private SiteService siteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private TopoService topoService;

	
	@GetMapping("/createSite")
	public String showSiteForm(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		int id = userConnected.getId();
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
	}
	
	@GetMapping("/user/sites")
	public String showListOfSitesOfUser(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
	   
	   int userId = userConnected.getId();
	   model.addAttribute("user", userConnected);
	  

	   List<Site> listUserSites= siteService.getUserAllSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "userSites";
	}

	
	@GetMapping("/sites")
   	public String showSitesListByPage(@AuthenticationPrincipal MyUserDetails userConnected, @RequestParam (value = "pageNumber") int currentPage, Model model) {
		
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
	public String getSite(@PathVariable(name = "id") int id, Site site, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		if (userConnected != null) {
			model.addAttribute("user", userConnected);
		}
		
		site = siteService.getSiteById(id);
		List<Area> listOfAreas= areaService.getAreasBySiteId(id);
		
		site.setAreas(listOfAreas);
		
		List<Topo> listOfTopos = topoService.getTopoBySiteId(id);

		model.addAttribute("site", site);
		model.addAttribute("toposOfTheSite", listOfTopos);
		
		return "site.html";
	}
	
	@GetMapping("/showSites")
	public String showNextPagesOfSitesToVisitors(@RequestParam (value = "pageNumber") int currentPage, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		if (userConnected != null) {
			model.addAttribute("user", userConnected);
		}
		
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
	public String getTagOnSite(@ModelAttribute ("site") Site site, @AuthenticationPrincipal MyUserDetails userConnected, int currentPage, Model model) {
		
		siteService.update(site.getId(), site.isTagged());
		
		return "redirect:/sites?pageNumber="+currentPage;
	}
}
