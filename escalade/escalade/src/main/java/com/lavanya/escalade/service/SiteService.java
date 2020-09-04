package com.lavanya.escalade.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Site> getAllSites(int pageNumber) {
		Sort sort = Sort.by("siteName").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		Page<Site> page = siteRepository.findAll(pageable);
		
		return page;
	}
	
	public void save (Site site) {
		
		List<Area> areasList= site.getAreas() ;
		for(Area value : areasList) {
			value.setSite(site);
		}
		siteRepository.save(site);
	}
	
	public void update (int id, boolean tagged) {
		
		siteRepository.updateTag(id, tagged);
	}
	
	public List<Site> getUserAllSites(int userId) {
		
		return siteRepository.findByUserId(userId);
		
	}
	
	public Site getSiteById(int id) {
		
		Optional<Site>  siteResponse = siteRepository.findById(id);
		Site site = siteResponse.get();
		return site;
	}
	
	public Page<Site> getTop4Sites(){
		
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(0, 4, sort);
		Page<Site> page = siteRepository.findAll(pageable);
		
		return page;
	}
	
}
