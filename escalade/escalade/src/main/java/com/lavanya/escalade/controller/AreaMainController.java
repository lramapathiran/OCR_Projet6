package com.lavanya.escalade.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.repository.AreaRepository;
import com.lavanya.escalade.service.AreaService;
import com.lavanya.escalade.service.UserService;

@Controller
public class AreaMainController {

	@Autowired 
	private AreaService areaService;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/createArea")
	public String showSiteForm(@RequestParam (value = "userId") int id, Model model) {
		
		User userConnected = userService.getUserById(id);
		int userId = userConnected.getId();
		model.addAttribute("user", userConnected);
		
		Area area = new Area();
//		area.setSiteId(siteId);
		model.addAttribute("area", area);

		  
	    return "addArea";
	}
	
	
//	@PostMapping("/saveSite")
//	public String saveSite(@Valid @ModelAttribute ("site") Site site, BindingResult result, Model model) {
//		
//		if (result.hasErrors()) {		 
//	          return "createSite";
//	    }
//		
//		siteService.save(site);
//		
//		int id = site.getUserId();
//		
////		"redirect:/user/"+id;
//		return "redirect:/user?userId="+id;
//	}
	
		
}
