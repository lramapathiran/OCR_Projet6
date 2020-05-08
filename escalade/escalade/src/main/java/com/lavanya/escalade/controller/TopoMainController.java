package com.lavanya.escalade.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.repository.TopoRepository;

@Controller
@RequestMapping(path="/escalade")
public class TopoMainController {
	
	@Autowired 
	private TopoRepository topoRepository;
	
	@PostMapping(path="/addTopo")
	public @ResponseBody String addNewTopo (@RequestParam String topoName, 
			@RequestParam String localization, @RequestParam String topoDescription, 
			@RequestParam Date topoDate, @RequestParam boolean isAvailable) {
	
	
		Topo topo = new Topo();
		topo.setTopoName(topoName);
		topo.setLocalization(localization);
		topo.setTopoDescription(topoDescription);
		topo.setTopoDate(topoDate);
		topo.setAvailable(isAvailable);
		
		topoRepository.save(topo);
		
		return "Saved";
	}

	@GetMapping(path="/topos")
	public @ResponseBody Iterable<Topo> getAllTopos() {
		// This returns a JSON or XML with the topos
		return topoRepository.findAll();
	}

}
