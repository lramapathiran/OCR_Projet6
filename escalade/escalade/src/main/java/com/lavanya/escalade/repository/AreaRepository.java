package com.lavanya.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Area;


@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

	@Query("select a from Area a JOIN a.site b where b.id = ?1")
	List <Area> findBySiteId(int siteId);
	
}
