package com.lavanya.escalade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Topo {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	int id;
	
	@Column (name = "user_id")
	@NotNull(message = "Ce champs est obligatoire")
	int userId;
	
	@Column (name = "site_id")
	@NotNull(message = "Ce champs est obligatoire")
	int siteId;
	
	@Column (name = "name")
	@NotBlank(message = "Ce champs est obligatoire")
	String topoName;
	
	@NotBlank(message = "Ce champs est obligatoire")
	String localization;
	
	@Column (name = "description")
	@NotBlank(message = "Ce champs est obligatoire")
	@Size(max = 200)
	String topoDescription;
	
	@Column (name = "date")
	@NotNull(message = "Ce champs est obligatoire")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date topoDate;
	
	@Column (name = "is_available")
	boolean available;
	
	public Topo() {
		
	}
	

	public Topo(String topoName, String localization, String topoDescription, Date topoDate, boolean available) {
		this.topoName = topoName;
		this.localization = localization;
		this.topoDescription = topoDescription;
		this.topoDate= topoDate;
		this.available = available;		
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public String getTopoName() {
		return topoName;
	}

	public void setTopoName(String topoName) {
		this.topoName = topoName;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public String getTopoDescription() {
		return topoDescription;
	}

	public void setTopoDescription(String topoDescription) {
		this.topoDescription = topoDescription;
	}

	public Date getTopoDate() {
		return topoDate;
	}

	public void setTopoDate(Date topoDate) {
		this.topoDate = topoDate;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "Topo(" + "id=" + id + ", userId=" + userId + ", siteId=" + siteId + ", topoName=" + topoName + ", localization=" + localization + ", topoDescription=" + topoDescription + ", topoDate=" + topoDate + ", available=" + available + "}";
	}
	

}
