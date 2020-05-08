package com.lavanya.escalade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.repository.SiteRepository;

@Controller
@RequestMapping(path="/escalade")
public class SiteMainController {
	
	@Autowired 
	private SiteRepository siteRepository;
	
	@PostMapping(path="/addSite")
	public @ResponseBody String addNewSite (@RequestParam String siteName, 
			@RequestParam String region, @RequestParam String department, 
			@RequestParam String city, @RequestParam int areasNumber, @RequestParam boolean isEquipped) {
	
	
		Site site = new Site();
		site.setSiteName(siteName);
		site.setRegion(region);
		site.setDepartment(department);
		site.setCity(city);
		site.setAreasNumber(areasNumber);
		site.setEquipped(isEquipped);
		
		siteRepository.save(site);
		
		return "Saved";
	}

	@GetMapping(path="/sites")
	public @ResponseBody Iterable<Site> getAllSites() {
		// This returns a JSON or XML with the sites
		return siteRepository.findAll();
	}
}
