package com.lavanya.escalade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.model.Comment;
import com.lavanya.escalade.model.Search;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.model.User;
import com.lavanya.escalade.service.AreaService;
import com.lavanya.escalade.service.CommentService;
import com.lavanya.escalade.service.MyUserDetails;
import com.lavanya.escalade.service.SiteService;
import com.lavanya.escalade.service.TopoService;
import com.lavanya.escalade.service.UserService;

/**
 * Controller used in MVC architecture to control all the requests related to Site object.
 * @author lavanya
 */
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
	
	@Autowired
	private CommentService commentService;
	
	/**
     * GET requests for /createSite endpoint.
     * This controller-method creates a new object Site and pass it to the form for the user to create a Site with all its attributes.
     *
     * @param model to pass data to the view.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return addSite.html
     */	
	@GetMapping("/createSite")
	public String showSiteForm(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		int id = userConnected.getId();
		model.addAttribute("user", userConnected);
		
		Site site = new Site();
		site.setUserId(id);
		model.addAttribute("site", site);

		  
	    return "addSite";
	}
	
	
	/**
	 * POST request to send data to /saveSite endpoint.
     * This controller-method is part of CRUD and is used to save a Site in database
     *
     * @param site is the object Site that needs to be saved.
     * @param result represents binding results, registers errors and allows for a Validator to be applied
     * @param model to pass data to the view.
     * @return site.html
     */	
	@PostMapping("/saveSite")
	public String saveSite(@Valid @ModelAttribute ("site") Site site, BindingResult result, Model model) {
		
		int id = site.getUserId();
		List<Area> areas = site.getAreas();
		User userConnected = userService.getUserById(id);
		model.addAttribute("user", userConnected);
		
		if (result.hasErrors()) {
			if (site.getAreasNumber() > 0) {
				if (areas.size() != site.getAreasNumber()) {
					model.addAttribute("message", "La section secteur ne contient pas le nombre de secteurs mentionné plus haut!");
					return "addSite";					
				}
				for (Area area : areas) {
						if (area.getAreaName() == "" || area.getCotationsRange() == "" || area.getRoutesNumber() == 0 || area.getRoutesNumber() == null) {
						model.addAttribute("message", "La section secteur n'a pas été entièrement renseignée!");
					}
				}
			}
	          return "addSite";
	    }
		
		if (site.getAreasNumber() > 0) {	
			if (areas.size() != site.getAreasNumber()) {
				model.addAttribute("message", "La section secteur ne contient pas le nombre de secteurs mentionné plus haut!");
				return "addSite";					
			}
			for (Area area : areas) {
					if (area.getAreaName() == "" || area.getCotationsRange() == "" || area.getRoutesNumber() == null || area.getRoutesNumber() == 0) {
					model.addAttribute("message", "La section secteur n'a pas été entièrement renseignée ou ne contient pas le nombre de secteurs mentionné plus haut!");
					return "addSite";
				}
			}
		}
		
		
		site.setTagged(false);
		siteService.save(site);
		
		int siteId = site.getId();
		
		
		return "redirect:/site/"+siteId;
	}
	
	/**
     * GET requests for /user/sites endpoint.
     * This controller-method retrieves from database all sites created by the authenticated user and pass that list to the view "userSites.html"
     * 
     * @param model to pass data to the view.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return userSites.html
     */	
	@GetMapping("/user/sites")
	public String showListOfSitesOfUser(@AuthenticationPrincipal MyUserDetails userConnected, Model model) {
	   
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
	  

	   List<Site> listUserSites= siteService.getUserAllSites(userId);
	   model.addAttribute("listUserSites", listUserSites);
	  
	   return "userSites";
	}

	
	/**
     * GET requests for /sitesList endpoint.
     * This controller-method retrieves from database all sites created by all users and pass that list to the view "sitesList.html"
     * 
     * @param model to pass data to the view.
     * @param currentPage, an int to specify which page of Sites to be displayed.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return sitesList.html
     */	
	@GetMapping("/sites")
   	public String showSitesListByPage(@AuthenticationPrincipal MyUserDetails userConnected, @RequestParam (value = "pageNumber") int currentPage, Model model) {
		

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
	
	/**
     * GET requests for /site/{id} endpoint.
     * This controller-method retrieves from database all data related to a Site of interest and the Comments/Areas/Topos associated to that Site. 
     * Those data are then passed to the model and displayed in the view "site.html".
     * 
     * @param model to pass data to the view.
     * @param siteId an int to specify the id of the Site object to be displayed.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @param site the object Site to be displayed in the view.
     * @return site.html
     */	
	@GetMapping("/site/{id}")
	public String getSite(@PathVariable(name = "id") int siteId, Site site, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		if (userConnected != null) {
			model.addAttribute("user", userConnected);
			
			int userId = userConnected.getId();
			
			Comment comment = new Comment();
			model.addAttribute("comment", comment);
			model.addAttribute("userId", userId);
			model.addAttribute("siteId", siteId);
			}
		

		List<Map<User,Comment>> siteCommentsList= new ArrayList<>();
		
		List<Comment> siteComments = commentService.getSiteComments(siteId);
		
		for(Comment comment : siteComments) {
			Map<User,Comment> map = new HashMap<>();
			int userId = comment.getUserId();
			User user = userService.getUserById(userId);
			map.put(user, comment);
			siteCommentsList.add(map);	
		}
			
		model.addAttribute("siteComments",siteCommentsList);
			
		site = siteService.getSiteById(siteId);
		List<Area> listOfAreas= areaService.getAreasBySiteId(siteId);
		
		site.setAreas(listOfAreas);
		
		List<Topo> listOfTopos = topoService.getTopoBySiteId(siteId);
	
		model.addAttribute("site", site);
		model.addAttribute("toposOfTheSite", listOfTopos);
		
		return "site";
	}
	
	
	/**
     * GET requests for /site/{id}/updateComment/{commentId} endpoint.
     * This controller-method retrieves the same data than the previous method, getSite() and pass them again to the view "site.html". 
     * It will also retrieve from database an object Comment for update purpose. 
     * 
     * @param model to pass data to the view.
     * @param siteId an int to specify the id of the Site object to be displayed.
     * @param commentId an int to specify the id of the Comment object to update.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @param site object Site to be displayed in the view.
     * @return site.html
     */	
	@GetMapping("/site/{id}/updateComment/{commentId}")
	public String displayComment(@PathVariable(name = "id") int siteId, @PathVariable(name = "commentId") int commentId, Site site, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		model.addAttribute("user", userConnected);
			
		List<Map<User,Comment>> siteCommentsList= new ArrayList<>();
			
		List<Comment> siteComments = commentService.getSiteComments(siteId);
		
		for(Comment comment : siteComments) {
			Map<User,Comment> map = new HashMap<>();
			int userId = comment.getUserId();
			User user = userService.getUserById(userId);
			map.put(user, comment);
			siteCommentsList.add(map);	
				
		}
		model.addAttribute("siteComments",siteCommentsList);
			
		int userId = userConnected.getId();
			
		Comment commentToUpdate = commentService.getCommentById(commentId);
		model.addAttribute("comment", commentToUpdate);
		model.addAttribute("userId", userId);
		model.addAttribute("siteId", siteId);

		site = siteService.getSiteById(siteId);
		List<Area> listOfAreas= areaService.getAreasBySiteId(siteId);
		
		site.setAreas(listOfAreas);
		
		List<Topo> listOfTopos = topoService.getTopoBySiteId(siteId);
		model.addAttribute("site", site);
		model.addAttribute("toposOfTheSite", listOfTopos);
		
		return "site";
 	}
	
	/**
     * GET requests for /showSites/{pageNumber} endpoint.
     * This controller-method retrieves all sites created by all users and display them as Page to the view "sitesListForVisitors.html".
     * 
     * @param model to pass data to the view.
     * @param currentPage an int to specify which page of Sites to be displayed.
     * @param keyword a String attribute from Search object used to filter a search sites by keyword.
     * @param department a String attribute from Search object used to filter a search sites by department.
     * @param areasNumber an Integer attribute from Search object used to filter a search sites by areasNumber.
     * @param routesNumber an Integer attribute from Search object used to filter a search sites by routesNumber.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return a model and view mav with view page as "sitesListForVisitors.html".
     */	
	@GetMapping("/showSites/{pageNumber}")
	public ModelAndView showNextPagesOfSitesToVisitors(@PathVariable(value = "pageNumber") int currentPage,@RequestParam(name="keyword", required=false) String keyword,
			@RequestParam(name="department", required=false) String department, @RequestParam(name="areasNumber", required=false) Integer areasNumber, 
			@RequestParam(name="routesNumber", required=false) Integer routesNumber, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		Search search = new Search();
		
		ModelAndView mav = new ModelAndView("sitesListForVisitors");
		if (userConnected != null) {
			model.addAttribute("user", userConnected);
		}
		
		
		if (keyword != null && keyword != "") {
			search.setKeyword(keyword);
		}
		
		if (department != null && department != "") {
			search.setDepartment(department);
		}
		
		search.setAreasNumber(areasNumber); 
		search.setRoutesNumber(routesNumber); 

		
		model.addAttribute("search", search);
		
		Page<Site> page = siteService.getAllSitesFiltered(currentPage, search);
		
		List<String> departmentList = siteService.getDepartmentList();
		List<Integer> areasNumberList = siteService.getAreasNumberList();
		List<Integer> routesNumberList = siteService.getRoutesNumberList();
				
		List<Site> sitesPage = page.getContent();
		int totalPages = page.getTotalPages();
		long totalSites = page.getTotalElements();
		
		model.addAttribute("sitesPage", sitesPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalSites", totalSites);
		model.addAttribute("selectedDepartment", department);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("areasNumberList", areasNumberList);
		model.addAttribute("routesNumberList", routesNumberList);
		
		return mav;
	}
	
	/**
	 * POST request to send data to /addTag endpoint.
     * This controller-method the attribute tagged of Site in order to tag or not a site. 
     *
     * @param site is the object Site that needs to be updated.
     * @param currentPage, an int to specify which page of Sites to be displayed.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @param model to pass data to the view.
     * @return the end point /sites
     */	
	@PostMapping("/addTag")
	public String getTagOnSite(@ModelAttribute ("site") Site site, @AuthenticationPrincipal MyUserDetails userConnected, int currentPage, Model model) {
		
		siteService.update(site.getId(), site.isTagged());
		
		return "redirect:/sites?pageNumber="+currentPage;
	}
}
