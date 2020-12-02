package com.lavanya.escalade.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Comment;
import com.lavanya.escalade.repository.CommentRepository;

/**
 * Service provider for all business functionalities related to Comment class.
 * @author lavanya
 */
@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * Method to save an object Comment in database.
	 * @param comment, object Comment to save in database.
	 */
	public void save(Comment comment) {
		
		LocalDateTime ldt = LocalDateTime.now();           

		comment.setCommentDate(ldt);

		commentRepository.save(comment);
	}
	
	/**
	 * Method to retrieve a particular comment identified by its id.
	 * @param id, id of the comment to identify the comment of interest in database.
	 * @return comment.
	 */
	public Comment getCommentById (int id) {
		Optional<Comment> commentResponse = commentRepository.findById(id);
		Comment comment = commentResponse.get();
		
		return comment;
	}
	
	/**
	 * Method to delete a particular comment from database.
	 * @param comment, comment to delete.
	 */
	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}
	
	
	/**
	 * Method to retrieve the list of all comments associated to a given Site.
	 * @param siteId, id of the site whose the comments to retrieve are associated.
	 * @return List of Comment.
	 */
	public List<Comment> getSiteComments(int siteId){
		
		List<Comment> ListOfSiteComments = commentRepository.findBySiteIdOrderByCommentDateDesc(siteId);
		
		return ListOfSiteComments;
	}
	
	/**
	 * Method to retrieve the list of all comments associated to a given User.
	 * @param userId, id of the user whose the comments to retrieve are associated.
	 * @return List of Comment.
	 */
	public List<Comment> getUserComments(int userId){
		
		List<Comment> ListOfUserComments = commentRepository.findByUserIdOrderByCommentDateDesc(userId);
		
		return ListOfUserComments;
	}
	
	/**
	 * Method to get a counting of comments made by a particular user.
	 * @param userId, id of the user whose the comments need to be counted.
	 * @return long, the total amount of comments saved by the user.
	 */
	public long getCommentsCountOfUser(int userId) {
		return commentRepository.countByUserId(userId);
	}
	
	/**
	 * Method to retrieve the five last comments saved in database using pagination. 
	 * @return Page of comments which are the 5 last comments registered in database.
	 */
	public Page<Comment> getLast5Comments(){
		
		Sort sort = Sort.by("commentDate").descending();
		Pageable pageable = PageRequest.of(0, 5, sort);
		Page<Comment> page = commentRepository.findAll(pageable);
		
		return page;
	}
	
	

}
