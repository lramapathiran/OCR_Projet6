package com.lavanya.escalade.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lavanya.escalade.model.Comment;
import com.lavanya.escalade.service.CommentService;
import com.lavanya.escalade.service.MyUserDetails;


/**
 * Controller used in MVC architecture to control all the requests related to Comment object.
 * @author lavanya
 */
@Controller
public class CommentMainController {
	
	@Autowired 
	private CommentService commentService;
	
	
	/**
	 * POST request to send data to /saveComment endpoint.
     * This controller-method is part of CRUD and is used to save a comment in database.
     *
     * @param comment the object Comment that needs to be saved.
     * @param result represents binding results, registers errors and allows for a Validator to be applied.
     * @param model to pass data to the view.
     * @param userConnected is the authenticated User passed within the object MyUserDetails.
     * @return the url /site.
     */
	@PostMapping("/saveComment")
	public String saveComment(@Valid @ModelAttribute ("comment") Comment comment, BindingResult result, Model model,@AuthenticationPrincipal MyUserDetails userConnected) {
	
		int siteId = comment.getSiteId();
		int userId = userConnected.getId();
		
		if (result.hasErrors()) {
			model.addAttribute("user", userConnected);
	          return "redirect:/site/" + siteId;
	    }
		
		comment.setUserId(userId);
		commentService.save(comment);
				
		return "redirect:/site/"+siteId;
	}
	
	/**
	 * POST request to send data to /updateComment endpoint.
     * This controller-method is part of CRUD and is used to update a comment in database.
     *
     * @param comment the object Comment to update.
     * @param result represents binding results, registers errors and allows for a validator to be applied.
     * @param model to pass data to the view.
     * @param userConnected is the authenticated User passed within the object MyUserDetails.
     * @return the url /site.
     */
	@PostMapping ("/updateComment")
	public String updateComment(@Valid @ModelAttribute ("comment") Comment comment, BindingResult result, @AuthenticationPrincipal MyUserDetails userConnected, Model model) {
		
		
		
		int siteId = comment.getSiteId();
		int id = comment.getCommentId();
		int userId = userConnected.getId();
		
		if (result.hasErrors()) {
			model.addAttribute("user", userConnected);
	          return "redirect:/site/" + siteId + "updateComment/" + id;
	    }
		
		comment.setUserId(userId);
		commentService.save(comment);
				
		return "redirect:/site/"+siteId;
	}
	
	/**
     * This controller-method is part of CRUD and is used to delete a comment from database.
     *
     * @param comment the object Comment to delete.
     * @param model to pass data to the view.
     * @return the url /site.
     */
	@PostMapping ("/deleteComment")
	public String deleteComment(Model model, @ModelAttribute("commentToDelete") Comment comment) {
		
		int siteId = comment.getSiteId();
		commentService.delete(comment);
		return "redirect:/site/"+siteId;
	}
	
	
	
	
}
