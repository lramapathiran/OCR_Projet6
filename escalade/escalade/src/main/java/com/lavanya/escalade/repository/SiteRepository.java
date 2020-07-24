package com.lavanya.escalade.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

	@Query("select u from Site u where u.userId = ?1")
	List <Site> findByUserId(int userId);
	
//	@Query("select s from Site s inner join fetch s.areas where s.siteId = :id")
//	Site findBySiteId(@Param("id") int id);

	
}
