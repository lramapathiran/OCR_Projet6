package com.lavanya.escalade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.enums.Reservation;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.repository.TopoRepository;

/**
 * Service provider for all business functionalities related to Topo class.
 * @author lavanya
 */
@Service
public class TopoService {
	
	@Autowired
	private TopoRepository topoRepository;
	
	
	/**
	 * method to retrieve all topos saved in database and displayed with pagination.
	 * @param pageNumber, int to access to the number of Topos Page to display.  
	 * @return Page of Topo.
	 */
	public Page<Topo> getAllTopos(int pageNumber) {
		
		Sort sort = Sort.by("topoName").ascending();
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		
		return topoRepository.findAll(pageable);
	}
	
	/**
	 * method to save an object Topo in database.
	 * @param topo, object Topo to save in database.
	 */
	public void save (Topo topo) {
		topoRepository.save(topo);
	}
	
	
	/**
	 * method to retrieve the list of all topos created by the User connected.
	 * @param userId, id of the user whose the topos belong.
	 * @return List of Topo.
	 */
	public List<Topo> getAllUserTopos(int userId) {		
		return topoRepository.findByUserId(userId);		
	}
	
	/**
	 * method to retrieve a list of topos associated to a given site.
	 * @param siteId, id of the site whose the topos are associated.
	 * @return List of Topo object.
	 */
	public List<Topo> getTopoBySiteId(int siteId) {		
		return topoRepository.findBySiteId(siteId);		
	}
	
	/**
	 * method to retrieve a particular topo identified by its id.
	 * @param id, id of the topo of interest to identify in database.
	 * @return Topo object.
	 */
	public Topo getTopoById(int id) {
		Optional<Topo>  topoResponse = topoRepository.findById(id);
		Topo topo = topoResponse.get();
		return topo;
	}
	
	/**
	 * method to retrieve the three last topos saved in database using pagination. 
	 * @return Page of topos which are the three last topos registered in database.
	 */
	public Page<Topo> getTop3Topos(){
		
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(0, 3, sort);
		
		return topoRepository.findAll(pageable);
	}
	
	
	/**
	 * method to get a counting of topos created by a particular user.
	 * @param userId, id of the user whose the topos need to be counted.
	 * @return long, the total amount of topos saved by the user.
	 */
	public long getToposCountOfUser(int userId) {
		return topoRepository.countByUserId(userId);
	}
	
	
	/**
	 * method to get a counting of topos of a particular user
	 * and whose the status of reservation is in hold of his response.
	 * @param userId, id of the user of interest.
	 * @return long, the total amount of topos with in hold status.
	 */
	public Long getCountOfToposToReserveByUser(int userId) {
		
		List<Topo> userTopos = topoRepository.findByUserId(userId);
		Long ToposToReserve = userTopos.stream().filter( c -> c.getReservation() == Reservation.I ).count();
		return ToposToReserve;
	}
	
	

}
