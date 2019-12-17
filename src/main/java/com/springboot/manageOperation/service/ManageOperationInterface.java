package com.springboot.manageOperation.service;

import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageOperationInterface {

	  public Flux<ManageOperation> findAll();
	  
	  public Mono<ManageOperation> findById(String id);
	  
	  public Mono<ManageOperation> saveSavings(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> saveCurrent(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> update(ManageOperation manageOperation,String id);
	  
	  public Mono<Void> delete(ManageOperation enterpriseCredit);
	  
//	  public Mono<SavingAccountDto> findByNumAccount(String numAccount);
	  
//	  public Mono<PersonalDto> searchDni(ManageOperation manageMovement);
	  
//	  public Mono<SavingAccountDto> updateClient(SavingAccountDto savingsAccountDto, String numAccount);



	
}
