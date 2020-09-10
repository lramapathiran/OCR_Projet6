package com.lavanya.escalade.repository;



import java.util.List;

import javax.transaction.Transactional;


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
}
