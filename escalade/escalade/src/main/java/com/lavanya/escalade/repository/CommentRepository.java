package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lavanya.escalade.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	@Query("select u from Comment u where u.userId = ?1")
	List <Comment> findByUserId(int userId);
	
	@Query("select u from Comment u where u.siteId = ?1")
	List <Comment> findBySiteId(int siteId);

	List<Comment> findBySiteIdOrderByCommentDateDesc(int id);
	
	List<Comment> findByUserIdOrderByCommentDateDesc(int id);
	
	public Long countByUserId(int userId);
}
