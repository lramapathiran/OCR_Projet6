package com.lavanya.escalade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.repository.AreaRepository;

/**
 * Service provider for all business functionalities related to Area class.
 * @author lavanya
 */
@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	/**
	 * method to retrieve the list of all areas saved in database.
	 * @return List of Area.
	 */
	public List<Area> getAllAreas() {
		
		return areaRepository.findAll();
	}
	
	/**
	 * method to save an object Area in database.
	 * @param area, object Area to save in database.
	 */
	public void save (Area area) {
		areaRepository.save(area);
	}
	
	
	/**
	 * method to retrieve the list of all areas associated to a given Site.
	 * @param siteId, id of the site whose the areas to retrieve are associated.
	 * @return List of Area.
	 */
	public List<Area> getAreasBySiteId(int siteId) {
		
		return areaRepository.findBySiteId(siteId);
	}
	

}
