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
import com.lavanya.escalade.model.Search;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.repository.SiteRepository;

/**
 * Service provider for all business functionalities related to Site class.
 * @author lavanya
 */
@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	/**
	 * method to retrieve all sites saved in database and displayed with pagination.
	 * @param pageNumber, int to access to the number of Sites Page to display.  
	 * @return Page of Site.
	 */
	public Page<Site> getAllSites(int pageNumber) {		
			
		Sort sort = Sort.by("siteName").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
		Page<Site> page = siteRepository.findAll(pageable);
		
		return page;
	}
	
	/**
	 * method to retrieve a list of sites resulting after filtering and displayed with pagination.
	 * @param pageNumber, int to access to the number of Sites' Page to display.
	 * @param search, Search object presenting all attributes used as filter element for the search of sites.  
	 * @return Page of Site resulting after filtering.
	 */
	public Page<Site> getAllSitesFiltered(int pageNumber, Search search) {
		
		Sort sort = Sort.by("siteName").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

				
		Page<Site> page = siteRepository.findFilteredSite(pageable, search);
		
		return page;
	}
	
	/**
	 * method to retrieve a distinct list of the department saved in database.
	 * The resulting list will be used as values in the department dropdown filter for sites search.  
	 * @return List of String of department distinct values.
	 */
	public List<String> getDepartmentList() {		
		return siteRepository.findDistinctDepartment();		
	}
	
	/**
	 * method to retrieve a distinct list of the areas number saved in database.
	 * The resulting list will be used as values in the areas dropdown filter for sites search.  
	 * @return List of String of areas number distinct values.
	 */
	public List<Integer> getAreasNumberList() {		
		return siteRepository.findDistinctAreasNumber();		
	}
	
	/**
	 * method to retrieve a distinct list of the routes number saved in database.
	 * The resulting list will be used as values in the routes dropdown filter for sites search.  
	 * @return List of String of routes number distinct values.
	 */
	public List<Integer> getRoutesNumberList() {		
		return siteRepository.findDistinctRoutesNumber();		
	}
	
	
	/**
	 * method to save an object Site in database.
	 * @param site, object Site to save in database.
	 */
	public void save (Site site) {
		
		List<Area> areasList= site.getAreas() ;
		for(Area value : areasList) {
			value.setSite(site);
		}
		siteRepository.save(site);
	}
	
	/**
	 * method to update the column is_tagged of a given site in site table in database.
	 * @param id, int to select the site object to update.
	 * @param tagged, boolean to set the value of the column is_tagged.
	 */
	public void update (int id, boolean tagged) {		
		siteRepository.updateTag(id, tagged);
	}
	
	/**
	 * method to retrieve the list of all sites created by the User connected.
	 * @param userId, id of the user whose the sites belong.
	 * @return List of Site.
	 */
	public List<Site> getUserAllSites(int userId) {		
		return siteRepository.findByUserId(userId);		
	}
	
	/**
	 * method to retrieve a particular site identified by its id.
	 * @param id, id of the site of interest to identify in database.
	 * @return Site object.
	 */
	public Site getSiteById(int id) {
		
		Optional<Site>  siteResponse = siteRepository.findById(id);
		Site site = siteResponse.get();
		return site;
	}
	
	/**
	 * method to retrieve the four last sites saved in database using pagination. 
	 * @return Page of sites which are the four last sites  registered in database.
	 */
	public Page<Site> getTop4Sites(){
		
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(0, 4, sort);
		Page<Site> page = siteRepository.findAll(pageable);
		
		return page;
	}
	
	/**
	 * method to get a counting of sites created by a particular user.
	 * @param userId, id of the user whose the sites need to be counted.
	 * @return long, the total amount of sites saved by the user.
	 */
	public long getSitesCountOfUser(int userId) {
		return siteRepository.countByUserId(userId);
	}
	
}
