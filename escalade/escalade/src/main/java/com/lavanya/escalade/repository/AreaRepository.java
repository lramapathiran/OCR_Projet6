package com.lavanya.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.escalade.model.Area;


@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

}
