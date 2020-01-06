package com.springboot.manageOperation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageTransfer;
import com.springboot.manageOperation.repo.ManageTransferRepo;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageTransferImpl implements ManageTrasferInterface{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageTransferImpl.class);

	
	@Autowired
	SavingAccountClient clientSavings;
	
	@Autowired
	ManageTransferRepo repo;

	@Override
	public Flux<ManageTransfer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ManageTransfer> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ManageTransfer> saveSavings(ManageTransfer manageTransfer) {
		
		return clientSavings.findByNumAccount(manageTransfer.getAccountOrigin()).flatMap(accountOrigin->{
			 accountOrigin.setBalance(accountOrigin.getBalance()-manageTransfer.getTransferAmount());
			 
			 return clientSavings.update(accountOrigin, accountOrigin.getId()).flatMap(originSave->{
				 
				 return clientSavings.findByNumAccount(manageTransfer.getAccountDestination()).flatMap(accountDestination->{
		             accountDestination.setBalance(accountDestination.getBalance()+manageTransfer.getTransferAmount());
					 
					   return clientSavings.update(accountDestination, accountDestination.getId()).flatMap(destinationSave->{
						   
						  LOGGER.info(destinationSave.toString());
						   return repo.save(manageTransfer);
					   });
					 
				 });
				 
			 });
        
       
			
			
		});
	}

	@Override
	public Mono<ManageTransfer> update(ManageTransfer ManageTransfer, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(ManageTransfer enterpriseCredit) {
		// TODO Auto-generated method stub
		return null;
	}

}
