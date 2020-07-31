package com.lavanya.escalade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.repository.TopoRepository;

@Service
public class TopoService {
	
	@Autowired
	private TopoRepository topoRepository;
	
	public List<Topo> getAllTopos() {
	
		return topoRepository.findAll();
	}
	
	public void save (Topo topo) {
		topoRepository.save(topo);
	}
	
	public List<Topo> getAllUserTopos(int userId) {
		
		return topoRepository.findByUserId(userId);
		
	}
	
	public List<Topo> getTopoBySiteId(int userId) {
		
		return topoRepository.findBySiteId(userId);
		
	}

	public Topo getTopoById(int id) {
		Optional<Topo>  topoResponse = topoRepository.findById(id);
		Topo topo = topoResponse.get();
		return topo;
	}

}
