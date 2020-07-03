package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Area {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column
	int id;
	
	@Column (name = "site_id")
	@NotNull
	int siteId;
	
	
	@Column (name = "name")
	@NotBlank(message = "Ce champs est obligatoire")
	String areaName;
	
	@Column (name = "routes_number")
	@NotBlank(message = "Ce champs est obligatoire")
	int routesNumber;
	
	public Area() {
		
	}

	public int getId() {
		return id;
	}

	public void setAreaId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getRoutesNumber() {
		return routesNumber;
	}

	public void setRoutesNumber(int routesNumber) {
		this.routesNumber = routesNumber;
	}
	
	@Override
	public String toString() {
		return "Area(" + "id=" + id + ", siteId=" + siteId + ", areaName=" + areaName + ", routesNumber=" + routesNumber + "}";
	}

}
