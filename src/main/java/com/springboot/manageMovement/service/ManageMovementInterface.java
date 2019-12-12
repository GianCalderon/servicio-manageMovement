package com.springboot.manageMovement.service;

import com.springboot.manageMovement.document.ManageMovement;
import com.springboot.manageMovement.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageMovementInterface {

	  public Flux<ManageMovement> findAll();
	  
	  public Mono<ManageMovement> findById(String id);
	  
	  public Mono<ManageMovement> save(ManageMovement manageMovement);
	  
	  public Mono<ManageMovement> update(ManageMovement manageMovement,String id);
	  
	  public Mono<Void> delete(ManageMovement enterpriseCredit);
	  
	  public Mono<PersonalDto> searchDni(ManageMovement manageMovement);
	  
//	  public Mono<PersonalCredit> saveDto(ManageMovement manageMovement);


	
}
