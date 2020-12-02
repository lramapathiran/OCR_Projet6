package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lavanya.escalade.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * Repository extending JPA repository for persistence of Comment object.
 * @author lavanya
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	/**
	 * Query to retrieve the list of comments created by a specific user identified by its id.
	 */
	@Query("select u from Comment u where u.userId = ?1")
	List <Comment> findByUserId(int userId);
	
	/**
	 * Query to retrieve the list of comments associated to a given site and ordered by date descendant.
	 */
	List<Comment> findBySiteIdOrderByCommentDateDesc(int id);
	
	/**
	 * Query to retrieve the list of comments created by a specific user and ordered by date descendant.
	 */
	List<Comment> findByUserIdOrderByCommentDateDesc(int id);
	
	/**
	 * Query to count the amount of comments created by a given user identified by its id.
	 */
	public Long countByUserId(int userId);
}
