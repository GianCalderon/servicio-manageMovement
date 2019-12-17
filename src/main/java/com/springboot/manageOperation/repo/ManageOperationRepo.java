package com.springboot.manageOperation.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.manageOperation.document.ManageOperation;

public interface ManageOperationRepo extends ReactiveMongoRepository<ManageOperation, String> {

}
