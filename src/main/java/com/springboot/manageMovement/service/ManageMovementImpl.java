package com.springboot.manageMovement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageMovement.document.ManageMovement;
import com.springboot.manageMovement.repo.ManageMovementRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageMovementImpl implements ManageMovementInterface {

	@Autowired
	ManageMovementRepo repo;
	
	
	@Override
	public Flux<ManageMovement> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<ManageMovement> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Mono<ManageMovement> save(ManageMovement manageMovement) {
		// TODO Auto-generated method stub
		return repo.save(manageMovement);
	}

	@Override
	public Mono<ManageMovement> update(ManageMovement manageMovement, String id) {
		// TODO Auto-generated method stub
//	    return repo.findById(id).flatMap(p -> {
//
//	    	p.setCreditAmount(personalCredit.getCreditAmount());
//	        p.setDateCredit(personalCredit.getDateCredit());
//	        p.setTea(personalCredit.getTea());
//	        
//	        return repo.save(p);
//
//	      });
		return null;
	}

	@Override
	public Mono<Void> delete(ManageMovement manageMovement) {
		// TODO Auto-generated method stub
		return repo.delete(manageMovement);
	}

}
