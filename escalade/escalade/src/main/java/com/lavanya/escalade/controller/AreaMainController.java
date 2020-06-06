package com.lavanya.escalade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Area;
import com.lavanya.escalade.repository.AreaRepository;

@Controller
@RequestMapping(path="/escalade")
public class AreaMainController {

	@Autowired 
	private AreaRepository areaRepository;
	
	@PostMapping(path="/addArea")
	public @ResponseBody String addNewArea (@RequestParam String areaName, 
			@RequestParam int routesNumber) {
	
		Area area = new Area();
		area.setAreaName(areaName);
		area.setRoutesNumber(routesNumber);
		
		areaRepository.save(area);
		
		return "Saved";
	}

	@GetMapping(path="/areas")
	public @ResponseBody Iterable<Area> getAllAreas() {
		// This returns a JSON or XML with the areas
		return areaRepository.findAll();
	}
		
}