package com.springboot.manageOperation.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.manageOperation.client.CorporativeAccountClient;
import com.springboot.manageOperation.client.CurrentAccountClient;
import com.springboot.manageOperation.client.CurrentAccountClientVip;
import com.springboot.manageOperation.client.PymeAccountClient;
import com.springboot.manageOperation.client.SavingAccountClient;
import com.springboot.manageOperation.client.SavingAccountClientVip;
import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.repo.ManageOperationRepo;
import com.springboot.manageOperation.util.TypeOperation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageOperationImpl implements ManageOperationInterface {
	
	
	@Autowired
	ManageOperationRepo repo;
	
	@Autowired
	SavingAccountClient clientSavings;
	
	@Autowired
	CurrentAccountClient clientCurrent;
	
	@Autowired
	SavingAccountClientVip clientSavingsVip;
	
	@Autowired
	CurrentAccountClientVip clientCurrentVip;
	
	@Autowired
	PymeAccountClient clientPyme;
	
	@Autowired
	CorporativeAccountClient clientCorporative;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageOperationImpl.class);
	
	@Override
	public Flux<ManageOperation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<ManageOperation> findById(String id) {

		return repo.findById(id);
	}
	
	@Override
	public Mono<ManageOperation> saveSavings(ManageOperation manageOperation) {
		
		LOGGER.info("service 1: "+manageOperation.toString());
		
		
	   return clientSavings.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
		   
			LOGGER.info("service 2: "+cuenta.toString());

			 Double comision=0.00;
			 if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;
				 
		 
			 manageOperation.setDateOperation(new Date());
			 manageOperation.setCommission(comision);
		  
		      
	       	return repo.save(manageOperation).flatMap(operacion->{

	            LOGGER.info("cantidad de movimientos ----> "+cuenta.getIdOperation().size());
	            
	            LOGGER.info(manageOperation.getTypeOperation().trim().toUpperCase());
	    		
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
	    			
	    			 LOGGER.info("cantidad de movimientos ----> "+manageOperation.getTypeOperation().trim().toUpperCase());

	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientSavings.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientSavings.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});

	     });
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveCurrent(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return clientCurrent.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
		   
		     Double comision=0.00;
			 if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;

		      manageOperation.setDateOperation(new Date());
	    	  return repo.save(manageOperation).flatMap(operacion->{
	
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {

	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCurrent.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCurrent.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});
	    	
	     });
	
	}
	
	@Override
	public Mono<ManageOperation> saveSavingsVip(ManageOperation manageOperation) {
		
		LOGGER.info("service 1: "+manageOperation.toString());
		
	   return clientSavingsVip.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
		   
			LOGGER.info("service 2: "+cuenta.toString());
			
			Double comision=0.00;
			if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;

		      manageOperation.setDateOperation(new Date());
	    	return repo.save(manageOperation).flatMap(operacion->{
	    			
	    		
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
	    	
	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientSavingsVip.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientSavingsVip.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});

	     });
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveCurrentVip(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return clientCurrentVip.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
	    	 
		   
		    Double comision=0.00;
			if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;
			
		      manageOperation.setDateOperation(new Date());
	    	  return repo.save(manageOperation).flatMap(operacion->{
	
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {

	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCurrentVip.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCurrentVip.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});
	    	
	     });
	
	}
	
	@Override
	public Mono<ManageOperation> savePyme(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return clientPyme.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
	    	 
		   
		    Double comision=0.00;
			if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;
			
		      manageOperation.setDateOperation(new Date());
	    	  return repo.save(manageOperation).flatMap(operacion->{
	
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {

	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientPyme.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientPyme.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});
	    	
	     });
	
	}
	
	@Override
	public Mono<ManageOperation> saveCorporative(ManageOperation manageOperation) {
		
		LOGGER.info("service: "+manageOperation.toString());
		
	   return clientCorporative.findByNumAccount(manageOperation.getNumberAccount()).flatMap(cuenta->{
		   
		   Double comision=0.00;
		   if(cuenta.getIdOperation().size()>TypeOperation.numMaxMovi)  comision=10.00;
	    	 
		      manageOperation.setDateOperation(new Date());
	    	  return repo.save(manageOperation).flatMap(operacion->{
	
	    		if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {

	    			cuenta.setBalance((cuenta.getBalance()-operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCorporative.update(cuenta,cuenta.getId()).block();
	    			
	    		}else if(manageOperation.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
	    			
	    			cuenta.setBalance((cuenta.getBalance()+operacion.getAmount())-operacion.getCommission());
	    		    cuenta.getIdOperation().add(operacion.getId());
	    		    cuenta.setUpdateDate(new Date());
	    		    clientCorporative.update(cuenta,cuenta.getId()).block();
	    		}
	
	    	return Mono.just(operacion);
	    	});
	    	
	     });
	
	}
	
	
	

	@Override
	public Mono<Void> delete(ManageOperation manageOperation) {
		// TODO Auto-generated method stub
		return repo.delete(manageOperation);
	}
	

	@Override
	public Mono<ManageOperation> update(ManageOperation manageOperation, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(m -> {

	    	m.setNumberAccount(manageOperation.getNumberAccount());
	    	m.setNumOperation(manageOperation.getNumOperation());
	    	m.setTypeOperation(manageOperation.getTypeOperation());
	    	m.setAmount(manageOperation.getAmount());
	    	
	        
	        return repo.save(m);

	      });
	
	}
	
	public Flux<ManageOperation> findByNumAccount(String numAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numAccount);

		return repo.findByNumberAccount(numAccount);
			
	
	}
	
	public Flux<ManageOperation> searchDate(String numberAccount,Date inicio,Date fin) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);
		LOGGER.info("FECHA INICIO :--->"+inicio);
		LOGGER.info("FECHA DE FIN :--->"+fin);

		return repo.findByNumberAccountAndDateOperationBetween(numberAccount,inicio,fin);
			
	
	}
	
	

	


}
