package com.springboot.manageOperation.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.manageOperation.dto.OperationDto;
import com.springboot.manageOperation.dto.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(SavingAccountClient.class);
	
	@Autowired
	private WebClient client;
	
	public Flux<SavingAccountDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(SavingAccountDto.class));
	}


	public Mono<SavingAccountDto> findById(String id) {
		
		LOGGER.info("NUMERO DE Id en client :--->"+id);
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccountDto.class);
		        
	}


	
	public Mono<SavingAccountDto> save(SavingAccountDto savingAccountDto) {
		
		LOGGER.info("listo a enviar: "+savingAccountDto.toString());
		
		return client.post().uri("/operation")
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(savingAccountDto))
			   .retrieve()
			   .bodyToMono(SavingAccountDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<SavingAccountDto> update(OperationDto operationDto, String id) {
		
		return client.post()
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(operationDto)
				   .retrieve()
				   .bodyToMono(SavingAccountDto.class);
	}
	
	public Mono<SavingAccountDto> findByNumAccount(String numCuenta) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numCuenta);
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get().uri("/{numCuenta}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccountDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

}
