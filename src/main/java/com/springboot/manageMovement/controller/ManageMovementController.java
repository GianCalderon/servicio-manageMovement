package com.springboot.manageMovement.controller;

import java.net.URI;

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

import com.springboot.manageMovement.document.ManageMovement;
import com.springboot.manageMovement.service.ManageMovementInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/manageMovement")
public class ManageMovementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageMovementController.class);


	  @Autowired
	 ManageMovementInterface service;

	  @GetMapping
	  public Mono<ResponseEntity<Flux<ManageMovement>>> toList() {

	    return Mono.just(ResponseEntity.ok()
	          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<ManageMovement>> search(@PathVariable String id) {

	    return service.findById(id).map(m -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(m))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @PostMapping
	  public Mono<ResponseEntity<ManageMovement>> save(@RequestBody  ManageMovement  manageMovement) {


	    return service.save(manageMovement).map(m -> ResponseEntity.created(URI.create("/api/manageMovement"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }

	  @PutMapping("/{id}")
	  public Mono<ResponseEntity<ManageMovement>> update(@RequestBody ManageMovement manageMovement,
	                    @PathVariable String id) {

	    return service.update(manageMovement, id)
	             .map(m -> ResponseEntity.created(URI.create("/api/manageMovement".concat(m.getId())))
	             .contentType(MediaType.APPLICATION_JSON).body(m))
	             .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @DeleteMapping("/{id}")
	  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

	    return service.findById(id).flatMap(m -> {
	      return service.delete(m).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
	    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	  }

}
