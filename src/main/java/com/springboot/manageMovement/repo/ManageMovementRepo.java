package com.springboot.manageMovement.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.manageMovement.document.ManageMovement;

public interface ManageMovementRepo extends ReactiveMongoRepository<ManageMovement, String> {

}
