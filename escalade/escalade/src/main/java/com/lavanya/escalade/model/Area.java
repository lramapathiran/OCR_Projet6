package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;


/**
 * Bean representing an Area.
 * Area object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Area {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column
	int id;
	
	
	@Column (name = "name")
	String areaName;
	
	@Column (name = "routes_number")
	@Positive
	Integer routesNumber;
	
	@Column (name = "cotations")
	String cotationsRange;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
	
	public Area() {
		
	}

	public String getCotationsRange() {
		return cotationsRange;
	}

	public void setCotationsRange(String cotationsRange) {
		this.cotationsRange = cotationsRange;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getRoutesNumber() {
		return routesNumber;
	}

	public void setRoutesNumber(Integer routesNumber) {
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
		return "Area(" + "id=" + id + ", areaName=" + areaName + ", routesNumber=" + routesNumber + ", site=" + site + ", cotationsRange=" + cotationsRange + "}";
	}
}
