package com.springboot.manageOperation.service;

import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageMovementInterface {

	  public Flux<SavingAccountDto> findAll();
	  
	  public Mono<SavingAccountDto> findById(String id);
	  
	  public Mono<ManageOperation> save(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> update(ManageOperation manageOperation,String id);
	  
	  public Mono<Void> delete(ManageOperation enterpriseCredit);
	  public Mono<SavingAccountDto> findByNum(String num);
	  
//	  public Mono<PersonalDto> searchDni(ManageOperation manageMovement);
	  
//	  public Mono<SavingAccountDto> updateClient(SavingAccountDto savingsAccountDto, String numAccount);



	
}
