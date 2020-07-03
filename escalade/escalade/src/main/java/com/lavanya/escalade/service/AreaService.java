package com.lavanya.escalade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.repository.AreaRepository;

@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	public List<Area> getAllsites() {
		
		return areaRepository.findAll();
	}
	
	public void save (Area area) {
		areaRepository.save(area);
	}
	
//	public List<Site> getAllSitesSites(int userId) {
//		
//		return siteRepository.findByUserId(userId);
//		
//	}
//	
//	public Site getSiteById(int id) {
//		
//		Optional<Site>  siteResponse = siteRepository.findById(id);
//		Site site = siteResponse.get();
//		return site;
//	}
	

}
