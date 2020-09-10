package com.lavanya.escalade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.escalade.enums.Reservation;
import com.lavanya.escalade.model.Area;
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
	
	@Autowired
	private SiteService siteService;
	
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
		
		int id = topo.getUserId();
//		String dateInString = topo.getTopoDate();
//		Date date = formatter.parse(dateInString);		
		if (result.hasErrors()) {
			User userConnected = userService.getUserById(id);
			model.addAttribute("user", userConnected);
	        return "createTopo";
	    }
		
		topo.setReservation(Reservation.A);
		topoService.save(topo);
		
		int topoId = topo.getId();
		
		return "redirect:/topo/"+topoId;
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
	
	@GetMapping("/topos")
   	public String showToposListByPage(@RequestParam ("userId") int id, @RequestParam ("pageNumber") int currentPage, Model model) {
		
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
		
		Page<Topo> page = topoService.getAllTopos(currentPage);
		
		List<Topo> toposPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalTopos = page.getTotalElements();
		
		model.addAttribute("toposPage", toposPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalTopos", totalTopos);
		
		
		return "toposList";

    }
	
	@GetMapping(value = {"/topo/{id}"})
	public String getSite(@PathVariable(name = "id") int id, Topo topo, Model model) {
		
		topo = topoService.getTopoById(id);
		int siteId = topo.getSiteId();
		int userId = topo.getUserId();
		
		String userEmail = userService.getUserById(userId).getEmail();
		
		Site site = siteService.getSiteById(siteId);
		String siteName = site.getSiteName();		

		model.addAttribute("topo", topo);
		model.addAttribute("siteName", siteName);
		model.addAttribute("topoCreatorEmail", userEmail);
		
		return "topo.html";
	}
	
	@PostMapping("/reservation")
	public String setReservation(Topo topo, Model model) {
		
		System.out.println(topo);
		int id = topo.getUserId();
		
		topoService.save(topo);
		
		return "redirect:/user/topos?userId="+id;
	}
	
	@PostMapping("/request/reservation")
	public String sendRequestForReservation(Topo topo, Model model) {
		
		int id = topo.getId();
		
		topoService.save(topo);
		
		return "redirect:/topo/" + id;
	}

	
	@GetMapping("/showTopos")
	public String showNextPagesOfToposToVisitors(@RequestParam (value = "pageNumber") int currentPage, Model model) {
		
		Page<Topo> page = topoService.getAllTopos(currentPage);
		
		List<Topo> toposPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalTopos = page.getTotalElements();
		
		model.addAttribute("toposPage", toposPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalSites", totalTopos);
		
		
		return "toposListForVisitors";
	}

}
