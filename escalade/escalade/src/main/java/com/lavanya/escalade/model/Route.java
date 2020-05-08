package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name = "id")
	int routeId;
	
	@Column (name = "name")
	String routeName;
	
	@Column (name = "cotation")
	String routeCotation;
	
	public Route() {
		
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteCotation() {
		return routeCotation;
	}

	public void setRouteCotation(String routeCotation) {
		this.routeCotation = routeCotation;
	}
	
}
