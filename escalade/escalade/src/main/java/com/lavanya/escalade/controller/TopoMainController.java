package com.lavanya.escalade.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.escalade.enums.Reservation;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.CommentService;
import com.lavanya.escalade.service.MyUserDetails;
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.TopoService;
import com.lavanya.escalade.service.UserService;

/**
 * Controller used in MVC architecture to control all the requests related to Topo object.
 * @author lavanya
 */
@Controller
public class TopoMainController {
	
	@Autowired 
	private TopoService topoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private CommentService commentService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	/**
     * GET requests for /createTopo/{siteId} endpoint.
     * This controller-method creates a new object Topo and pass it to the form for the user to create a Topo with all its attributes.
     * 
     * @param id, int to specify the site's id whose the topo in creation is associated.
     * @param model 
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return addTopo.html
     */	
	@GetMapping("/createTopo/{siteId}")
	public String showTopoForm(@PathVariable("siteId") int id, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		

		Site site = siteService.getSiteById(id);
		String city = site.getCity();
		
		model.addAttribute("user", userConnected);
		model.addAttribute("city", city);
		
		Topo topo = new Topo();
		topo.setSiteId(id);
		topo.setUserId(userConnected.getId());
		model.addAttribute("topo", topo);

		  
	    return "addTopo";
	}
	
	/**
	 * POST request to send data to /saveTopo endpoint.
     * This controller-method is part of CRUD and is used to save a Topo in database
     *
     * @param topo is the object Topo that needs to be saved.
     * @param result represents binding results, registers errors and allows for a Validator to be applied
     * @param model
     * @return the url /topo/{id}
     */	
	@PostMapping("/saveTopo")
	public String saveTopo(@Valid @ModelAttribute ("topo") Topo topo, BindingResult result, Model model) {
		
		int id = topo.getUserId();
		if (result.hasErrors()) {
			User userConnected = userService.getUserById(id);
			model.addAttribute("user", userConnected);
	        return "addTopo";
	    }
		
		topo.setReservation(Reservation.A);
		topoService.save(topo);
		
		int topoId = topo.getId();
		
		return "redirect:/topo/"+topoId;
	}
	
	
	/**
     * GET requests for /user/topos endpoint.
     * This controller-method retrieves from database all topos created by the authenticated user and pass that list to the view "userTopos.html"
     * 
     * @param model 
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return userTopos.html
     */	
	@GetMapping("/user/topos")
	public String showListOfToposOfUser(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
	   int userId = userConnected.getId();
	   model.addAttribute("user", userConnected);
	   
	   long sitesCount = siteService.getSitesCountOfUser(userId);
	   model.addAttribute("sitesCount", sitesCount);
		   
	   long toposCount = topoService.getToposCountOfUser(userId);
	   model.addAttribute("toposCount", toposCount);
	  
	   long commentsCount = commentService.getCommentsCountOfUser(userId);
	   model.addAttribute("commentsCount", commentsCount);
		  
	   long toposToReserve = topoService.getCountOfToposToReserveByUser(userId);
	   model.addAttribute("toposToReserve", toposToReserve);
	  
	   List<Topo> listUserTopos= topoService.getAllUserTopos(userId);
	   model.addAttribute("listUserTopos", listUserTopos);
	  
	   return "userTopos";
	}
	
	/**
     * GET requests for /topos endpoint.
     * This controller-method retrieves from database all topos created by all users and pass that list to the view "toposList.html"
     * 
     * @param model 
     * @param currentPage, an int to specify which page of Topos to display.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return toposList.html
     */	
	@GetMapping("/topos")
   	public String showToposListByPage(@AuthenticationPrincipal MyUserDetails userConnected, @RequestParam ("pageNumber") int currentPage, Model model) {
		
		int userId = userConnected.getId();
		model.addAttribute("user", userConnected);
		   
		long sitesCount = siteService.getSitesCountOfUser(userId);
		model.addAttribute("sitesCount", sitesCount);
			   
		long toposCount = topoService.getToposCountOfUser(userId);
		model.addAttribute("toposCount", toposCount);
		  
		long commentsCount = commentService.getCommentsCountOfUser(userId);
		model.addAttribute("commentsCount", commentsCount);
			  
		long toposToReserve = topoService.getCountOfToposToReserveByUser(userId);
		model.addAttribute("toposToReserve", toposToReserve);
		
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
	
	/**
     * GET requests for /topo/{id} endpoint.
     * This controller-method retrieves from database all data related to a Topo of interest. 
     * Those data are then passed to the model and displayed in the view "topo.html".
     * 
     * @param model 
     * @param id, an int to specify the id of the Topo object to display.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return topo.html
     */	
	@GetMapping(value = {"/topo/{id}"})
	public String getTopo(@AuthenticationPrincipal MyUserDetails userConnected, @PathVariable(name = "id") int id, Topo topo, Model model) {
		
		topo = topoService.getTopoById(id);
		int siteId = topo.getSiteId();
		int userId = topo.getUserId();
		
		String userEmail = userService.getUserById(userId).getEmail();
		
		Site site = siteService.getSiteById(siteId);
		String siteName = site.getSiteName();		
		
		model.addAttribute("user", userConnected);
		model.addAttribute("topo", topo);
		model.addAttribute("siteName", siteName);
		model.addAttribute("topoCreatorEmail", userEmail);
		
		return "topo.html";
	}
	
	/**
	 * POST request to send data to /reservation endpoint.
     * This controller-method is part of CRUD and is used to update a Topo in database.
     * The update concerns the reservation attribute of Topo to accept or not a reservation.
     *
     * @param topo is the object Topo whose reservation attribute needs to be updated.
     * @param model
     * @return the url /user/topos
     */	
	@PostMapping("/reservation")
	public String setReservation(Topo topo, Model model) {
		
		topoService.save(topo);
		
		return "redirect:/user/topos";
	}
	
	
	/**
	 * POST request to send data to /request/reservation endpoint.
     * This controller-method is part of CRUD and is used to update a Topo in database.
     * The update concerns the reservation attribute of Topo to request a reservation.
     *
     * @param topo is the object Topo whose reservation attribute needs to be updated.
     * @param model
     * @return the url /user/topos
     */	
	@PostMapping("/request/reservation")
	public String sendRequestForReservation(Topo topo, Model model) {
		
		int id = topo.getId();
		
		topoService.save(topo);
		
		return "redirect:/topo/" + id;
	}

	
	
	/**
     * GET requests for /showTopos endpoint.
     * This controller-method retrieves all topos created by all users and display them as Page to the view "toposListForUserConnected.html".
     * 
     * @param model 
     * @param currentPage, an int to specify which page of Topos to display.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return "toposListForUserConnected.html".
     */	
	@GetMapping("/showTopos")
	public String showNextPagesOfToposToVisitors(@AuthenticationPrincipal MyUserDetails userConnected, @RequestParam (value = "pageNumber") int currentPage, Model model) {
		
		Page<Topo> page = topoService.getAllTopos(currentPage);
		
		List<Topo> toposPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalTopos = page.getTotalElements();
		
		model.addAttribute("user", userConnected);
		model.addAttribute("toposPage", toposPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalSites", totalTopos);
		
		
		return "toposListForUserConnected";
	}

}
