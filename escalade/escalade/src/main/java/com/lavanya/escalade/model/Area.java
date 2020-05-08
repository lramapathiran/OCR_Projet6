package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Area {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name = "id")
	int areaId;
	
	@Column (name = "name")
	String areaName;
	
	@Column (name = "routes_number")
	int routesNumber;
	
	public Area() {
		
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
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

}
