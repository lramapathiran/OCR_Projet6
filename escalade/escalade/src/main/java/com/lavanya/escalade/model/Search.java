package com.lavanya.escalade.model;


/**
 * Bean representing a Search.
 * This class gathered all attributes required to make sites search according to one or a combination of filters.
 * @author lavanya
 */
public class Search {

	String keyword;
	String department;
	Integer areasNumber;
	Integer routesNumber; 
	
	public Search() {
		
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getAreasNumber() {
		return areasNumber;
	}

	public void setAreasNumber(Integer areasNumber) {
		this.areasNumber = areasNumber;
	}

	public Integer getRoutesNumber() {
		return routesNumber;
	}

	public void setRoutesNumber(Integer routesNumber) {
		this.routesNumber = routesNumber;
	}
	
	
}
