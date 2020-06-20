package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Topo;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer>{
	
	@Query("select u from Topo u where u.userId = ?1")
	List <Topo> findByUserId(int userId);
	
	@Query("select u from Topo u where u.siteId = ?1")
	List <Topo> findBySiteId(int siteId);

}
