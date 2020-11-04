package com.lavanya.escalade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.repository.AreaRepository;

@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	public List<Area> getAllsites() {
		
		return areaRepository.findAll();
	}
	
	public void save (Area area) {
		areaRepository.save(area);
	}
	
	
	public List<Area> getAreasBySiteId(int siteId) {
		
		return areaRepository.findBySiteId(siteId);
	}
	

}
