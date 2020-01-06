package com.springboot.manageOperation.controller;

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

import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.document.ManageTransfer;
import com.springboot.manageOperation.dto.OperationDto;
import com.springboot.manageOperation.service.ManageTrasferInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transferAccount")
public class ManageTransferController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageTransferController.class);


	  @Autowired
	  ManageTrasferInterface service;

	  
	  @PostMapping("/savings")
	  public Mono<ResponseEntity<ManageTransfer>> saveSavings(@RequestBody  ManageTransfer manageTransfer) {
		  LOGGER.info("controller :"+manageTransfer.toString());

	    return service.saveSavings(manageTransfer).map(m -> ResponseEntity.created(URI.create("/api/transferAccount"))
	                  .contentType(MediaType.APPLICATION_JSON).body(m));

	  }





	  



}
