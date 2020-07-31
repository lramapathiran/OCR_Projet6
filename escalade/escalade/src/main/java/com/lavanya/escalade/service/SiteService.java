package com.lavanya.escalade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.repository.SiteRepository;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	public List<Site> getAllSites() {
	
		return siteRepository.findAll();
	}
	
	public void save (Site site) {
		
		List<Area> areasList= site.getAreas() ;
		for(Area value : areasList) {
			value.setSite(site);
		}
		siteRepository.save(site);
	}
	
	public List<Site> getUserAllSites(int userId) {
		
		return siteRepository.findByUserId(userId);
		
	}
	
	public Site getSiteById(int id) {
		
		Optional<Site>  siteResponse = siteRepository.findById(id);
		Site site = siteResponse.get();
		return site;
	}
	
}
