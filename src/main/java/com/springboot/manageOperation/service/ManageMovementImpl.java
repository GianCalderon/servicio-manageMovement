package com.springboot.manageOperation.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SavingAccountDto;
import com.springboot.manageOperation.repo.ManageMovementRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageMovementImpl implements ManageMovementInterface {
	
	
	@Autowired
	ManageMovementRepo repo;
	
	@Autowired
	SavingAccountClient client;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageMovementImpl.class);
	
	@Override
	public Flux<SavingAccountDto> findAll() {
		// TODO Auto-generated method stub
		return client.findAll();
	}

	@Override
	public Mono<SavingAccountDto> findById(String id) {

		return client.findById(id);
	}
	
	@Override
	public Mono<ManageOperation> save(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return client.findByNumAccount(manageOperation.getNumberAccount()).flatMap(p->{
	    	 
	    	return repo.save(manageOperation).flatMap(m->{
	    		
	    		p.setBalance(m.getAmount());
	    		client.save(p);
	    		
	    		return Mono.just(manageOperation);
	    	});

	     });
	
	}

	@Override
	public Mono<ManageOperation> update(ManageOperation manageOperation, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(m -> {

	    	m.setNumberAccount(manageOperation.getNumberAccount());
	    	m.setNumOperation(manageOperation.getNumOperation());
	    	m.setTipoMovement(manageOperation.getTipoMovement());
	    	m.setAmount(manageOperation.getAmount());
	    	m.setDateMovement(new Date());
	        
	        return repo.save(m);

	      });
	
	}

	@Override
	public Mono<Void> delete(ManageOperation manageOperation) {
		// TODO Auto-generated method stub
		return repo.delete(manageOperation);
	}
	
	public Mono<SavingAccountDto> findByNum(String num) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+num);

		return client.findById(num);
	}
	


}
