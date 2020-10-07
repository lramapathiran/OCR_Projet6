package com.lavanya.escalade.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.escalade.error.UserAlreadyExistException;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.UserService;
import com.lavanya.escalade.service.MyUserDetails;
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.TopoService;

@Controller // This means that this class is a Controller
public class UserMainController {
  
	@Autowired
    private UserService userService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private TopoService topoService;
	
  
	@GetMapping("/")
	public String showHomePage(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		if (userConnected != null) {
			model.addAttribute("user", userConnected);
		}
		
		Page<Site> sitePage = siteService.getTop4Sites();		
		List<Site> top4Sites = sitePage.getContent();
		long totalSites = sitePage.getTotalElements();
		
		Page<Topo> topoPage = topoService.getTop3Topos();		
		List<Topo> top3Topos = topoPage.getContent();
		long totalTopos = topoPage.getTotalElements();
		
		int totalUsers = userService.getTotalUsersRegistered();
		
		model.addAttribute("top4Sites", top4Sites);
		model.addAttribute("totalSites", totalSites);
		model.addAttribute("top3Topos", top3Topos);
		model.addAttribute("totalTopos", totalTopos);
		model.addAttribute("totalUsers", totalUsers);
		
		return "index";
	}

	

    @GetMapping("/signup")
	public String showSignUpForm (Model model) {
	  
    	User user = new User();
    	model.addAttribute("user", user);	  
    	return "addUser";
	}
  
    @PostMapping("/saveUser")
	public String saveUser (@ModelAttribute("user") @Valid User user,BindingResult result, final HttpServletRequest request, Model model,final Errors errors) {
	  
    	if (result.hasErrors()) {	  
    		return "addUser";
    	}
    	
    	try {
        	user.setRoles("USER");
        	user.setActive(true);
        	userService.save(user);
	    } catch (final UserAlreadyExistException uaeEx) {
	        model.addAttribute("user", user);
	        model.addAttribute("message", "Cet adresse existe déjà, veuillez renseigner une autre adresse email!");
            return "addUser";
	    }
     

	 
      	return "redirect:/user";
	}
  
    
    @GetMapping("/users")
   	public String showUsersListByPage(@AuthenticationPrincipal MyUserDetails userConnected, @RequestParam ("pageNumber") int currentPage, Model model) {
		
		model.addAttribute("user", userConnected);
		
		Page<User> page = userService.getAllUsers(currentPage);
		
		List<User> usersPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalUsers = page.getTotalElements();
		
		model.addAttribute("usersPage", usersPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalUsers", totalUsers);
		
		
		return "usersList";

    }
    
 
    @GetMapping("/user")
	public String showUserConnectedHomePage(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
	   
	   
	   int userId = userConnected.getId();
	   model.addAttribute("user", userConnected);
	  
	   
	   List<Site> listUserSites= siteService.getUserAllSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "userHome";
	}
   
   
    
}
