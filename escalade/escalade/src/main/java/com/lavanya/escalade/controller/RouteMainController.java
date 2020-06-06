package com.lavanya.escalade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lavanya.escalade.model.Route;
import com.lavanya.escalade.repository.RouteRepository;

@Controller
@RequestMapping(path="/escalade")
public class RouteMainController {
	
	@Autowired 
	private RouteRepository routeRepository;
	
	@PostMapping(path="/addRoute")
	public @ResponseBody String addNewRoute (@RequestParam String routeName, 
			@RequestParam String routeCotation) {
	
		Route route = new Route();
		route.setRouteName(routeName);
		route.setRouteCotation(routeCotation);
		
		routeRepository.save(route);
		
		return "Saved";
	}

	@GetMapping(path="/routes")
	public @ResponseBody Iterable<Route> getAllRoutes() {
		// This returns a JSON or XML with the routes
		return routeRepository.findAll();
	}

}