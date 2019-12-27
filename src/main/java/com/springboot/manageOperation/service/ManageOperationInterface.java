package com.springboot.manageOperation.service;

import java.util.Date;

import com.springboot.manageOperation.document.ManageOperation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageOperationInterface {

	  public Flux<ManageOperation> findAll();
	  
	  public Mono<ManageOperation> findById(String id);
	  
	  public Mono<ManageOperation> saveSavings(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> saveCurrent(ManageOperation manageOperation);
	  
      public Mono<ManageOperation> saveSavingsVip(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> saveCurrentVip(ManageOperation manageOperation);
	  
      public Mono<ManageOperation> savePyme(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> saveCorporative(ManageOperation manageOperation);
	  
	  public Mono<ManageOperation> update(ManageOperation manageOperation,String id);
	  
	  public Mono<Void> delete(ManageOperation enterpriseCredit);
	  
	  public Flux<ManageOperation> findByNumAccount(String numAccount);
	  
	  public Flux<ManageOperation> searchDate(String numberAccount,Date inicio,Date fin);
	  
	  
	  
//	  public Mono<SavingAccountDto> findByNumAccount(String numAccount);
	  
//	  public Mono<PersonalDto> searchDni(ManageOperation manageMovement);
	  
//	  public Mono<SavingAccountDto> updateClient(SavingAccountDto savingsAccountDto, String numAccount);



	
}
