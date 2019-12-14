package com.springboot.manageOperation.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SavingAccountDto;
import com.springboot.manageOperation.service.ManageMovementInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/manageOperation")
public class ManageMovementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageMovementController.class);


	  @Autowired
	 ManageMovementInterface service;
	  
	  @Autowired
	  SavingAccountClient  client;
	  
	  

	  @GetMapping
	  public Mono<ResponseEntity<Flux<SavingAccountDto>>> toList() {

	    return Mono.just(ResponseEntity.ok()
	          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<SavingAccountDto>> search(@PathVariable String id) {

		 LOGGER.info("NUMERO DE Id Controller :--->"+id);
		  
	    return client.findById(id).map(m -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(m))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @PostMapping
	  public Mono<ResponseEntity<ManageOperation>> save(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.save(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }

	  @PutMapping("/{id}")
	  public Mono<ResponseEntity<ManageOperation>> update(@RequestBody ManageOperation manageOperation,
	                    @PathVariable String id) {

	    return service.update(manageOperation, id)
	             .map(m -> ResponseEntity.created(URI.create("/api/manageOperation".concat(m.getId())))
	             .contentType(MediaType.APPLICATION_JSON).body(m))
	             .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

//	  @DeleteMapping("/{id}")
//	  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
//
//	    return service.findById(id).flatMap(m -> {
//	      return service.delete(m).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
//	    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
//
//	  }
	  
	  
//	  @GetMapping("cuenta/{numAccount}")
//	  public Mono<ResponseEntity<SavingAccountDto>> searchCuenta(@PathVariable String numAccount) {
//
//	    return service.findByNum(numAccount).map(m -> ResponseEntity.ok()
//	      .contentType(MediaType.APPLICATION_JSON).body(m))
//	      .defaultIfEmpty(ResponseEntity.notFound().build());
//
//	  }


}
