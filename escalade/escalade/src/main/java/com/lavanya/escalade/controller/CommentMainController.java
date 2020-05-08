package com.lavanya.escalade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Comment;
import com.lavanya.escalade.repository.CommentRepository;

@Controller
@RequestMapping(path="/escalade")
public class CommentMainController {
	
	@Autowired 
	private CommentRepository commentRepository;
	
	@PostMapping(path="/addComment")
	public @ResponseBody String addNewComment (@RequestParam String commentContent) {
	
	
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		
		commentRepository.save(comment);
		
		return "Saved";
	}
	
	@GetMapping(path="/comments")
	public @ResponseBody Iterable<Comment> getAllComments() {
		// This returns a JSON or XML with the areas
		return commentRepository.findAll();
	}

}
