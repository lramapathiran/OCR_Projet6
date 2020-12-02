package com.lavanya.escalade.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 * Bean representing a Comment.
 * Comment object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name = "id")
	int commentId;
	
	@Column (name = "description")
	String commentContent;
	
	@Column (name = "time_stamp")
	LocalDateTime commentDate;
	
	@Column (name="user_id")
	int userId;
	
	@Column (name="site_id")
	@NotNull
	int siteId;
	
	public Comment() {
		
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	
}
