package com.springboot.manageMovement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageMovement.client.PersonalClient;
import com.springboot.manageMovement.document.ManageMovement;
import com.springboot.manageMovement.dto.PersonalDto;
import com.springboot.manageMovement.repo.ManageMovementRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageMovementImpl implements ManageMovementInterface {

	@Autowired
	ManageMovementRepo repo;
	
	  @Autowired
	  PersonalClient client;
	
	
	@Override
	public Flux<ManageMovement> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<ManageMovement> findById(String id) {

		return repo.findById(id);
	}

	@Override
	public Mono<ManageMovement> save(ManageMovement manageMovement) {
		
		searchDni(manageMovement).filter(p-> p.get)
		
	
		manageMovement.setDateMovement(new Date());
		return repo.save(manageMovement);
	}

	@Override
	public Mono<ManageMovement> update(ManageMovement manageMovement, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(m -> {

	    	m.setNumAccount(manageMovement.getNumAccount());
	    	m.setNumOperation(manageMovement.getNumOperation());
	    	m.setTipoMovement(manageMovement.getTipoMovement());
	    	m.setAmount(manageMovement.getAmount());
	    	m.setDateMovement(new Date());
	        
	        return repo.save(m);

	      });
	
	}

	@Override
	public Mono<Void> delete(ManageMovement manageMovement) {
		// TODO Auto-generated method stub
		return repo.delete(manageMovement);
	}
	
	@Override
	public Mono<PersonalDto> searchDni(ManageMovement manageMovement) {
		
		return client.findAll().filter(p->p.getNumDoc().equals(manageMovement.getNumDoc())).next();
			
		

		
	}

}
