package com.lavanya.escalade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.lavanya.escalade.enums.Reservation;

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
	@Size(max = 500)
	String topoDescription;
	
	@Column (name = "date")
	@NotNull(message = "Ce champs est obligatoire")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date topoDate;
	
	@Enumerated(EnumType.STRING)
	Reservation reservation;

	public Topo() {
		
	}
	

	public Topo(String topoName, String localization, String topoDescription, Date topoDate, Reservation reservation) {
		this.topoName = topoName;
		this.localization = localization;
		this.topoDescription = topoDescription;
		this.topoDate= topoDate;
		this.reservation = reservation;
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

	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	@Override
	public String toString() {
		return "Topo(" + "id=" + id + ", userId=" + userId + ", siteId=" + siteId + ", topoName=" + topoName + ", localization=" + localization + ", topoDescription=" + topoDescription + ", topoDate=" + topoDate + ", reservation=" + reservation + "}";
	}
	

}
