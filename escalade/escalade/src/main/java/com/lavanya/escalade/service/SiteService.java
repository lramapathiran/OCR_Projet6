package com.lavanya.escalade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Site> getAllSitesFilteredByKeyword(int pageNumber, String keyword) {
		
		Sort sort = Sort.by("siteName").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		
		if(keyword == null) {
			return siteRepository.findAll(pageable);
		}
		
		
		Page<Site> page = siteRepository.findAll(pageable, keyword);
		
		return page;
	}
	
	public Page<Site> getAllSitesFiltered(int pageNumber, String keyword, String department, Integer areasNumber, Integer routesNumber) {
		
		Sort sort = Sort.by("siteName").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		
		
				
		Page<Site> page = siteRepository.findFilteredSite(pageable, keyword, department, areasNumber, routesNumber);
		
		return page;
	}
	
	public List<String> getDepartmentList() {
		
		return siteRepository.findDistinctDepartment();
		
	}
	
	public List<Integer> getAreasNumberList() {
		
		return siteRepository.findDistinctAreasNumber();
		
	}
	
	
	public List<Integer> getRoutesNumberList() {
		
		return siteRepository.findDistinctRoutesNumber();
		
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
	
	public long getSitesCountOfUser(int userId) {
		return siteRepository.countByUserId(userId);
	}
	
}
