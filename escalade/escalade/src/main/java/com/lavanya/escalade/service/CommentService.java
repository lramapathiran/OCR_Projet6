package com.lavanya.escalade.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Comment;
import com.lavanya.escalade.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void save(Comment comment) {
		
		LocalDateTime ldt = LocalDateTime.now();           

		comment.setCommentDate(ldt);

		commentRepository.save(comment);
	}
	
	public Comment getCommentById (int id) {
		Optional<Comment> commentResponse = commentRepository.findById(id);
		Comment comment = commentResponse.get();
		
		return comment;
	}
	
	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}
	
	public List<Comment> getSiteComments(int siteId){
		
		List<Comment> ListOfSiteComments = commentRepository.findBySiteIdOrderByCommentDateDesc(siteId);
		
		return ListOfSiteComments;
	}
	
	public List<Comment> getUserComments(int userId){
		
		List<Comment> ListOfUserComments = commentRepository.findByUserIdOrderByCommentDateDesc(userId);
		
		return ListOfUserComments;
	}
	
	public long getCommentsCountOfUser(int userId) {
		return commentRepository.countByUserId(userId);
	}
	
	public Page<Comment> getLast5Comments(){
		
		Sort sort = Sort.by("commentDate").descending();
		Pageable pageable = PageRequest.of(0, 5, sort);
		Page<Comment> page = commentRepository.findAll(pageable);
		
		return page;
	}
	
	

}
