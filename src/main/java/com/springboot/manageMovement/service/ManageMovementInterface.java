package com.springboot.manageMovement.service;

import com.springboot.manageMovement.document.ManageMovement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageMovementInterface {

	  public Flux<ManageMovement> findAll();
	  
	  public Mono<ManageMovement> findById(String id);
	  
	  public Mono<ManageMovement> save(ManageMovement manageMovement);
	  
	  public Mono<ManageMovement> update(ManageMovement manageMovement,String id);
	  
	  public Mono<Void> delete(ManageMovement enterpriseCredit);
	  
//	  public Mono<PersonalCredit> saveDto(ManageMovement manageMovement);


	
}
