package com.springboot.manageOperation.service;

import java.util.Date;

import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.OperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageOperationInterface {

	  public Flux<ManageOperation> findAll();
	  
	  public Mono<ManageOperation> findById(String id);
	  
	  public Mono<ManageOperation> saveSavings(OperationDto operationDto);
	  
	  public Mono<ManageOperation> saveCurrent(OperationDto operationDto);
	  
      public Mono<ManageOperation> saveSavingsVip(OperationDto operationDto);
	  
	  public Mono<ManageOperation> saveCurrentVip(OperationDto operationDto);
	  
      public Mono<ManageOperation> savePyme(OperationDto operationDto);
	  
	  public Mono<ManageOperation> saveCorporative(OperationDto operationDto);
	  
	  public Mono<ManageOperation> update(ManageOperation manageOperation,String id);
	  
	  public Mono<Void> delete(ManageOperation enterpriseCredit);
	  
	  public Flux<ManageOperation> findByNumAccount(String numAccount);
	  
	  public Flux<ManageOperation> searchDate(String numberAccount,Date inicio,Date fin);
	  
	
	  
	  
	  
//	  public Mono<SavingAccountDto> findByNumAccount(String numAccount);
	  
//	  public Mono<PersonalDto> searchDni(ManageOperation manageMovement);
	  
//	  public Mono<SavingAccountDto> updateClient(SavingAccountDto savingsAccountDto, String numAccount);



	
}
