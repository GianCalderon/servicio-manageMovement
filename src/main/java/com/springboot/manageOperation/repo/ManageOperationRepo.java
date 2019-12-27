package com.springboot.manageOperation.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.manageOperation.document.ManageOperation;

import reactor.core.publisher.Flux;

@Repository
public interface ManageOperationRepo extends ReactiveMongoRepository<ManageOperation, String> {
	
	
	Flux<ManageOperation> findByNumberAccount(String numberAccount);

//	Flux<ManageOperation> findByDateOperationBeetwen(Date dateOperation);
	
	Flux<ManageOperation> findByNumberAccountAndDateOperationBetween(String numberAccount,Date inicio, Date fin);
	


}
