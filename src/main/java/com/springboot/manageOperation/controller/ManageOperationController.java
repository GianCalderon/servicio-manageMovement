package com.springboot.manageOperation.controller;

import java.net.URI;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.SearchDate;
import com.springboot.manageOperation.service.ManageOperationInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/operation")
public class ManageOperationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageOperationController.class);


	  @Autowired
	  ManageOperationInterface service;
	  
	  @Autowired
	  SavingAccountClient  client;
	  
	 
	  

	  @GetMapping
	  public Mono<ResponseEntity<Flux<ManageOperation>>> toList() {

	    return Mono.just(ResponseEntity.ok()
	          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<ManageOperation>> search(@PathVariable String id) {

		 LOGGER.info("NUMERO DE Id Controller :--->"+id);
		  
	    return service.findById(id).map(m -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(m))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  
	  @PostMapping("/savings")
	  public Mono<ResponseEntity<ManageOperation>> saveSavings(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.saveSavings(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }
	  @PostMapping("/current")
	  public Mono<ResponseEntity<ManageOperation>> saveCurrent(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.saveCurrent(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }
	  
	  
	  @PostMapping("/savingsVip")
	  public Mono<ResponseEntity<ManageOperation>> saveSavingsVip(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.saveSavingsVip(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }
	  @PostMapping("/currentVip")
	  public Mono<ResponseEntity<ManageOperation>> saveCurrentVipaaaaaaaaa(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.saveCurrentVip(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }
	  
	  @PostMapping("/pyme")
	  public Mono<ResponseEntity<ManageOperation>> savePyme(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.savePyme(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }


	  @PostMapping("/corporative")
	  public Mono<ResponseEntity<ManageOperation>> saveCorporative(@RequestBody  ManageOperation  manageOperation) {
		  LOGGER.info("controller :"+manageOperation.toString());

	    return service.saveCorporative(manageOperation).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
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

	  @DeleteMapping("/{id}")
	  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

	    return service.findById(id).flatMap(m -> {
	      return service.delete(m).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
	    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	  }
	  
	  
//	  @GetMapping("account/{numAccount}")
//	  public Mono<ResponseEntity<SavingAccountDto>> searchCuenta(@PathVariable String numAccount) {
//
//	    return client.findByNumAccount(numAccount).map(m -> ResponseEntity.ok()
//	      .contentType(MediaType.APPLICATION_JSON).body(m))
//	      .defaultIfEmpty(ResponseEntity.notFound().build());
//
//	  }
	  
//    @GetMapping("account/{numberAccount}")
//	  public Mono<ResponseEntity<Flux<ManageOperation>>> searchCuenta(@PathVariable String numberAccount) {
//
//	    return service.findByNumAccount(numberAccount).map(m -> ResponseEntity.ok()
//	      .contentType(MediaType.APPLICATION_JSON).body(m))
//	      .defaultIfEmpty(ResponseEntity.notFound().build());
//
//	  }
	  
	  @GetMapping("account/{numberAccount}")
	  public Mono<ResponseEntity<Flux<ManageOperation>>> searchCuenta(@PathVariable String numberAccount) {

		    return Mono.just(ResponseEntity.ok()
		          .contentType(MediaType.APPLICATION_JSON).body(service.findByNumAccount(numberAccount)));

		  }
	  

	  
	  //GUARDA DIRECTAMENTE AL MICROSERVICIO SAVINGACCOUNT
//	  @PostMapping
//	  public Mono<ResponseEntity<SavingAccountDto>> save(@RequestBody  SavingAccountDto  savingAccountDto) {
//		  LOGGER.info("controller :"+savingAccountDto.toString());
//
//	    return client.save(savingAccountDto).map(m -> ResponseEntity.created(URI.create("/api/manageOperation"))
//	                  .contentType(MediaType.APPLICATION_JSON).body(m));
//
//	  }
//	  
	  @PostMapping("/ope")
	  public Flux<ManageOperation> toListXDate(@RequestBody SearchDate dato) {

	    return service.searchDate(dato.getNumberAccount(), dato.getInicio(), dato.getFin());

	  }
	  



}
