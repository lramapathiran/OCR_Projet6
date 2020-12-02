package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Area;

/**
 * Repository extending JPA repository for persistence of Area object.
 * @author lavanya
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

	/**
	 * Query to retrieve the list of area of a specific site
	 * @param siteId for id of the site of interest.
	 * @return List of Area.
	 */
	@Query("select a from Area a JOIN a.site b where b.id = ?1")
	List <Area> findBySiteId(int siteId);
	
}
