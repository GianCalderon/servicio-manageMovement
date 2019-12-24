package com.springboot.manageOperation.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.manageOperation.document.ManageOperation;

import reactor.core.publisher.Flux;

public interface ManageOperationRepo extends ReactiveMongoRepository<ManageOperation, String> {
	
	
	Flux<ManageOperation> findByNumberAccount(String numberAccount);
	


}
