package com.lavanya.escalade.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Area {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column
	int id;
	
//	@Column (name = "site_id")
//	@NotNull
//	int siteId;
	
	
	@Column (name = "name")
	@NotBlank(message = "Ce champs est obligatoire")
	String areaName;
	
	@Column (name = "routes_number")
	@NotBlank(message = "Ce champs est obligatoire")
	int routesNumber;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
	
	public Area() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getSiteId() {
//		return siteId;
//	}
//
//	public void setSiteId(int siteId) {
//		this.siteId = siteId;
//	}

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
	
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Area(" + "id=" + id + ", areaName=" + areaName + ", routesNumber=" + routesNumber + "}";
	}
//	siteId=" + siteId + ",
}
