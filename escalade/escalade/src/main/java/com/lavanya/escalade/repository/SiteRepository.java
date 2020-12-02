package com.lavanya.escalade.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Search;
import com.lavanya.escalade.model.Site;

/**
 * Repository extending JPA repository for persistence of Site object.
 * @author lavanya
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
	
	/**
	 * Query to retrieve the list of site of a specific user.
	 */
	@Query("select u from Site u where u.userId = ?1")
	List <Site> findByUserId(int userId);
	
	/**
	 * Query to update the column is_tagged in site table.
	 */
	@Query("update Site u set u.tagged=?2 where u.id = ?1")
	@Modifying
	@Transactional
	void updateTag(int id, boolean tagged);
	
	/**
	 * Query to retrieve distinct values in the column department from table site.
	 */
	@Query("select distinct u.department from Site u order by u.department asc")
    List<String> findDistinctDepartment();
	
	/**
	 * Query to retrieve distinct values in the column areas_number from table site.
	 */
	@Query("select distinct u.areasNumber from Site u order by u.areasNumber asc")
    List<Integer> findDistinctAreasNumber();
	
	/**
	 * Query to retrieve distinct values in the column routes_number from table area.
	 */
	@Query("select distinct a.routesNumber from Site u join u.areas a order by a.routesNumber asc")
    List<Integer> findDistinctRoutesNumber();
	
	/**
	 * Query to retrieve a list of sites using pagination based or not with the following filters criteria:
	 * keyword that can be site name, region or areas name,
	 * site department number,
	 * site areas number,
	 * and site routes number.
	 */
	@Query("select distinct u from Site u left join u.areas a where "
			+ "(:#{#search.keyword} is null or concat(u.siteName, u.region, u.city, a.areaName) like concat('%',:#{#search.keyword},'%')) and "
			+ "(:#{#search.department} is null or u.department = :#{#search.department}) and "
			+ "(:#{#search.areasNumber} is null or u.areasNumber = :#{#search.areasNumber}) and "
			+ "(:#{#search.routesNumber} is null or a.routesNumber = :#{#search.routesNumber})"
			)	
	public Page<Site> findFilteredSite(Pageable pageable, @Param ("search") Search search);
	
	/**
	 * Query to count the amount of sites created by a given user identified by its id.
	 */
	public Long countByUserId(int userId);
}
