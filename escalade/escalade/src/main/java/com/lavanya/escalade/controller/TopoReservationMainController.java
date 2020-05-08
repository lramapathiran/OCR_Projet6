package com.lavanya.escalade.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.TopoReservation;
import com.lavanya.escalade.repository.TopoReservationRepository;

@Controller
@RequestMapping(path="/escalade")
public class TopoReservationMainController {
	
	@Autowired 
	private TopoReservationRepository topoReservationRepository;
	
	@PostMapping(path="/addTopoReservation")
	public @ResponseBody String addNewTopoReservation (@RequestParam String message, 
			@RequestParam Date requestDate) {
	
	
		TopoReservation topoReservation = new TopoReservation();
		topoReservation.setMessage(message);
		topoReservation.setRequestDate(requestDate);
		
		topoReservationRepository.save(topoReservation);
		
		return "Saved";
	}

	@GetMapping(path="/topoReservations")
	public @ResponseBody Iterable<TopoReservation> getAllTopoReservations() {
		// This returns a JSON or XML with the topoReservations
		return topoReservationRepository.findAll();
	}

}
