package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Site {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	int id;

	@Column (name = "creator_id")
	@NotBlank
	int userId;

	@Column (name = "name")
	@NotBlank(message = "Ce champs est obligatoire")
	String siteName;
	
	@NotBlank(message = "Ce champs est obligatoire")
	String region;
	
	@NotBlank(message = "Ce champs est obligatoire")
	@Size(min = 2, max = 2)
	String department;
	
	@NotBlank(message = "Ce champs est obligatoire")
	String city;
	
	@Column (name = "areas_number")
	@NotBlank(message = "Ce champs est obligatoire")
	int areasNumber;
	
	@Column (name = "is_equipped")
	boolean equipped;
	
	public Site() {
		
	}
	
	public Site(String siteName, String region, String department, String city, int areasNumber, boolean equipped) {
		this.siteName = siteName;
		this.region = region;
		this.department = department;
		this.city = city;
		this.areasNumber = areasNumber;
		this.equipped = equipped;		
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


	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAreasNumber() {
		return areasNumber;
	}

	public void setAreasNumber(int areasNumber) {
		this.areasNumber = areasNumber;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}

	
	@Override
	public String toString() {
		return "Site(" + "id=" + id + ", userId=" + userId + ", siteName=" + siteName + ", region=" + region + ", department=" + department + ", city=" + city + ", areasNumber=" + areasNumber + ", equipped=" + equipped + "}";
	}

}
