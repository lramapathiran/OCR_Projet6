package com.lavanya.escalade.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.enums.Reservation;
import com.lavanya.escalade.model.Site;
import com.lavanya.escalade.model.Topo;
import com.lavanya.escalade.repository.TopoRepository;

@Service
public class TopoService {
	
	@Autowired
	private TopoRepository topoRepository;
	
	public Page<Topo> getAllTopos(int pageNumber) {
		
		Sort sort = Sort.by("topoName").ascending();
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		
		return topoRepository.findAll(pageable);
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
	
	public Page<Topo> getTop3Topos(){
		
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(0, 3, sort);
		
		return topoRepository.findAll(pageable);
	}
	
	public long getToposCountOfUser(int userId) {
		return topoRepository.countByUserId(userId);
	}
	
	public Long getCountOfToposToReserveByUser(int userId) {
		
		List<Topo> userTopos = topoRepository.findByUserId(userId);
		Long ToposToReserve = userTopos.stream().filter( c -> c.getReservation() == Reservation.I ).count();
		return ToposToReserve;
	}
	
	

}
