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

@Controller
public class CommentMainController {
	
	@Autowired 
	private CommentService commentService;
	
	public String redirectToLoginPage(MyUserDetails userConnected, Model model) {
		
		if (userConnected == null) {
			   String nonConnectedMessage = "Vous n'êtes pas connecté!"; 
			   model.addAttribute("nonConnectedMessage", nonConnectedMessage);
		   }
		
		return "login";
	}

	
	@PostMapping("/saveComment")
	public String saveComment(@Valid @ModelAttribute ("comment") Comment comment, BindingResult result, Model model,@AuthenticationPrincipal MyUserDetails userConnected) {
		
		redirectToLoginPage(userConnected, model);
		

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
	
	@PostMapping ("/deleteComment")
	public String deleteComment(Model model, @ModelAttribute("commentToDelete") Comment comment) {
		
		int siteId = comment.getSiteId();
		commentService.delete(comment);
		return "redirect:/site/"+siteId;
	}
	
	
	
	
}
