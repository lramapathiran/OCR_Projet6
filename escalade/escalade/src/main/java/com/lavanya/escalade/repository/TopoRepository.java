package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Topo;

/**
 * Repository extending JPA repository for persistence of Topo object.
 * @author lavanya
 */
@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer>{
	
	/**
	 * Query to retrieve the list of topo of a specific user.
	 */
	@Query("select u from Topo u where u.userId = ?1")
	List <Topo> findByUserId(int userId);
	
	/**
	 * Query to retrieve the list of topo of a specific site.
	 */
	@Query("select u from Topo u where u.siteId = ?1")
	List <Topo> findBySiteId(int siteId);
	
	/**
	 * Query to count the amount of topos created by a given user identified by its id.
	 */
	public Long countByUserId(int userId);
	

}
