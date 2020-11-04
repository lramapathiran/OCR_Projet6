package com.lavanya.escalade.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
	
	@Query("select u from Site u where u.userId = ?1")
	List <Site> findByUserId(int userId);
	
	@Query("update Site u set u.tagged=?2 where u.id = ?1")
	@Modifying
	@Transactional
	void updateTag(int id, boolean tagged);
	
	@Query("select u from Site u where "
			+ "concat(u.siteName, u.region, u.city)"
			+ " like %?1%")
	public Page<Site> findAll(Pageable pageable, String keyword);
	
	public Page<Site> findByDepartment(Pageable pageable, String department);
	
	public Page<Site> findByAreasNumber(Pageable pageable, int areasNumber);
	
	@Query("select distinct u.department from Site u order by u.department asc")
    List<String> findDistinctDepartment();
		
	@Query("select distinct u.areasNumber from Site u order by u.areasNumber asc")
    List<Integer> findDistinctAreasNumber();
	
	@Query("select distinct a.routesNumber from Site u join u.areas a order by a.routesNumber asc")
    List<Integer> findDistinctRoutesNumber();
	
	@Query("select u from Site u join u.areas a where "
			+ "(?1 is null or concat(u.siteName, u.region, u.city, a.areaName) like %?1%) and "
			+ "(?2 is null or u.department = ?2) and "
			+ "(?3 is null or u.areasNumber = ?3) and "
			+ "(?4 is null or a.routesNumber = ?4)"
			)	
	public Page<Site> findFilteredSite(Pageable pageable, String keyword, String department, Integer areasNumber, Integer routesNumber);
	
	public Long countByUserId(int userId);
}
