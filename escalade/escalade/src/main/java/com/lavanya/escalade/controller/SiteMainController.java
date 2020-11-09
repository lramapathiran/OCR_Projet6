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
	
	@GetMapping("/site/{id}")
	public String getSite(@PathVariable(name = "id") int siteId, Site site, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		if (userConnected != null) {
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
			
			Comment comment = new Comment();
			model.addAttribute("comment", comment);
			model.addAttribute("userId", userId);
			model.addAttribute("siteId", siteId);

		}
		
		site = siteService.getSiteById(siteId);
		List<Area> listOfAreas= areaService.getAreasBySiteId(siteId);
		
		site.setAreas(listOfAreas);
		
		List<Topo> listOfTopos = topoService.getTopoBySiteId(siteId);
	
		model.addAttribute("site", site);
		model.addAttribute("toposOfTheSite", listOfTopos);
		
		return "site";
	}
	
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
	
	
	@GetMapping("/showSites/{pageNumber}")
	public ModelAndView showNextPagesOfSitesToVisitors(@PathVariable(value = "pageNumber") int currentPage,@RequestParam(name="keyword", required=false) String keyword,
			@RequestParam(name="department", required=false) String department, @RequestParam(name="areasNumber", required=false) Integer areasNumber, @RequestParam(name="routesNumber", required=false) Integer routesNumber, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
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
	

	@PostMapping("/addTag")
	public String getTagOnSite(@ModelAttribute ("site") Site site, @AuthenticationPrincipal MyUserDetails userConnected, int currentPage, Model model) {
		
		siteService.update(site.getId(), site.isTagged());
		
		return "redirect:/sites?pageNumber="+currentPage;
	}
}
