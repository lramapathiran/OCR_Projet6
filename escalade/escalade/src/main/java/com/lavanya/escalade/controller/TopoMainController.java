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

import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.model.User;

import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.TopoService;
import com.lavanya.escalade.service.UserService;

@Controller
public class TopoMainController {
	
	@Autowired 
	private TopoService topoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/createTopo/{siteId}/{userId}")
	public String showTopoForm(@PathVariable("siteId") int id, @PathVariable("userId") int userId, Model model) {
		
		User userConnected = userService.getUserById(userId);
		
		model.addAttribute("user", userConnected);
		
		Topo topo = new Topo();
		topo.setSiteId(id);
		topo.setUserId(userId);
		model.addAttribute("topo", topo);

		  
	    return "addTopo";
	}
	
	
	@PostMapping("/saveTopo")
	public String saveTopo(@Valid @ModelAttribute ("topo") Topo topo, BindingResult result, Model model) {
		
		
//		String dateInString = topo.getTopoDate();
//		Date date = formatter.parse(dateInString);		
		if (result.hasErrors()) {		 
	          return "createTopo";
	    }
		
		topo.setAvailable(true);
		topoService.save(topo);
		
		int id = topo.getUserId();
		
//		"redirect:/user/"+id;
		return "redirect:/user?userId="+id;
	}
	
	@GetMapping("/user/topos")
	public String showListOfToposOfUser(@RequestParam (value = "userId") int id, User user, Model model) {
	   
	   user = userService.getUserById(id);
	   int userId = user.getId();
	   model.addAttribute("user", user);
	  

	   List<Topo> listUserTopos= topoService.getAllUserTopos(userId);
	   model.addAttribute("listUserTopos", listUserTopos);
	  
	   return "userTopos";
	}
	
	
//	@GetMapping("/sites")
//   	public String showUsersList(@RequestParam (value = "userId") int id, Model model) {
//		
//		User userConnected = userService.getUserById(id);
//		model.addAttribute("user", userConnected);
//	   
//		List<Site> listOfSites= siteService.sitesList();
//		model.addAttribute("listOfSites", listOfSites);
//		
//		return "sitesList";
//
//    }

}
