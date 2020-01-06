package com.springboot.manageOperation.service;

import com.springboot.manageOperation.document.ManageTransfer;
import com.springboot.manageOperation.dto.OperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageTrasferInterface {

	  public Flux<ManageTransfer> findAll();
	  
	  public Mono<ManageTransfer> findById(String id);
	  
	  public Mono<ManageTransfer> saveSavings(ManageTransfer manageTransfer);
	  
//	  public Mono<ManageTransfer> saveCurrent(OperationDto operationDto);
//
//      public Mono<ManageTransfer> saveSavingsVip(OperationDto operationDto);
//	  
//	  public Mono<ManageTransfer> saveCurrentVip(OperationDto operationDto);
//	  
//      public Mono<ManageTransfer> savePyme(OperationDto operationDto);
//	  
//	  public Mono<ManageTransfer> saveCorporative(OperationDto operationDto);
//	  
	  public Mono<ManageTransfer> update(ManageTransfer ManageTransfer,String id);
	  
	  public Mono<Void> delete(ManageTransfer enterpriseCredit);
//	  
//	  public Flux<ManageTransfer> findByNumAccount(String numAccount);
//	  
//	  public Flux<ManageTransfer> searchDate(String numberAccount,Date inicio,Date fin);
//	  
	

	
}
