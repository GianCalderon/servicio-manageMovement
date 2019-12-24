package com.springboot.manageOperation.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageOperation.client.CurrentAccountClient;
import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SavingAccountDto;
import com.springboot.manageOperation.repo.ManageOperationRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageOperationImpl implements ManageOperationInterface {
	
	
	@Autowired
	ManageOperationRepo repo;
	
	@Autowired
	SavingAccountClient clientSavings;
	
	@Autowired
	CurrentAccountClient clientCurrent;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageOperationImpl.class);
	
	@Override
	public Flux<ManageOperation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<ManageOperation> findById(String id) {

		return repo.findById(id);
	}
	
	@Override
	public Mono<ManageOperation> saveSavings(ManageOperation manageOperation) {
		
		LOGGER.info("service 1: "+manageOperation.toString());
		
	   return clientSavings.findByNumAccount(manageOperation.getNumberAccount()).flatMap(p->{
		   
			LOGGER.info("service 2: "+p.toString());

		      manageOperation.setDateOperation(new Date());
	    	return repo.save(manageOperation).flatMap(m->{
	    			
	    		
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals("DEBITO")) {

	    			p.setBalance(p.getBalance()-m.getAmount());
	    		    p.getIdOperation().add(m.getId());
	    		    p.setUpdateDate(new Date());
	    		    clientSavings.update(p,p.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals("ABONO")) {
	    			
	    			p.setBalance(p.getBalance()+m.getAmount());
	    		    p.getIdOperation().add(m.getId());
	    		    p.setUpdateDate(new Date());
	    		    clientSavings.update(p,p.getId()).block();
	    		}
	
	    	return Mono.just(m);
	    	});

	     });
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveCurrent(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return clientCurrent.findByNumAccount(manageOperation.getNumberAccount()).flatMap(p->{
	    	 
		      manageOperation.setDateOperation(new Date());
	    	  return repo.save(manageOperation).flatMap(m->{
	
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals("DEBITO")) {

	    			p.setBalance(p.getBalance()-m.getAmount());
	    		    p.getIdOperation().add(m.getId());
	    		    p.setUpdateDate(new Date());
	    		    clientCurrent.update(p,p.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals("ABONO")) {
	    			
	    			p.setBalance(p.getBalance()+m.getAmount());
	    		    p.getIdOperation().add(m.getId());
	    		    p.setUpdateDate(new Date());
	    		    clientCurrent.update(p,p.getId()).block();
	    		}
	
	    	return Mono.just(m);
	    	});
	    	
	     });
	
	}

	@Override
	public Mono<ManageOperation> update(ManageOperation manageOperation, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(m -> {

	    	m.setNumberAccount(manageOperation.getNumberAccount());
	    	m.setNumOperation(manageOperation.getNumOperation());
	    	m.setTypeOperation(manageOperation.getTypeOperation());
	    	m.setAmount(manageOperation.getAmount());
	    	
	        
	        return repo.save(m);

	      });
	
	}

	@Override
	public Mono<Void> delete(ManageOperation manageOperation) {
		// TODO Auto-generated method stub
		return repo.delete(manageOperation);
	}
	
	public Flux<ManageOperation> findByNumAccount(String numAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numAccount);

		return repo.findByNumberAccount(numAccount);
			
	
	}
	

	


}
