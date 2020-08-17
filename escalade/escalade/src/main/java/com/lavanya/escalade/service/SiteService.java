package com.lavanya.escalade.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
	
	public Slice<Site> getTopTenSite() {
		
		Slice<Site> slice = null;
		
	    Pageable pageable = (Pageable) PageRequest.of(0, 3, Sort.by("id").descending());
	    
	    slice = siteRepository.findTop3ById(3, pageable);
	    
	    return slice;
	      
	}
	
}
