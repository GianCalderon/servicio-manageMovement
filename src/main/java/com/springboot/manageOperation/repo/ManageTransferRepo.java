package com.springboot.manageOperation.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.manageOperation.document.ManageTransfer;

@Repository
public interface ManageTransferRepo extends ReactiveMongoRepository<ManageTransfer, String> {
	
	


}
