package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name = "id")
	int siteId;
	
	@Column (name = "name")
	String siteName;
	
	String region;
	
	String department;
	
	String city;
	
	@Column (name = "areas_number")
	int areasNumber;
	
	@Column (name = "is_equipped")
	boolean isEquipped;
	
	public Site() {
		
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
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
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	

}
