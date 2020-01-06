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
import com.springboot.manageOperation.dto.OperationDto;
import com.springboot.manageOperation.repo.ManageOperationRepo;
import com.springboot.manageOperation.util.TypeOperation;
import com.springboot.manageOperation.util.UtilConvert;

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
	
	@Autowired
	UtilConvert convert;
	
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
	public Mono<ManageOperation> saveSavings(OperationDto operationDto) {
		
       return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(cantOperation->{
			
			LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+cantOperation.toString());

			return clientSavings.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
				
				  Double comision=0.0;
				  if(cantOperation>=10) comision=10.0;
				
				     if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
				
				       cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
    		           cuenta.setUpdateDate(new Date());
    		           operationDto.setComision(comision);
    		        
				     }else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
					
					   cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
	    		       cuenta.setUpdateDate(new Date());
	    		        operationDto.setComision(comision);
				   }

    		    return  clientSavings.update(cuenta,cuenta.getId()).flatMap(account->{
    		    	
    		    	 return repo.save(convert.convertOperation(operationDto));
    		    });
    		   

			});
			
			
		
	   });
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveCurrent(OperationDto operationDto) {
		
		 return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(sizeAccount->{
				
				LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+sizeAccount.toString());

				return clientCurrent.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
					
					LOGGER.info("CurrentAccount :--->"+cuenta.toString());
					
					Double comision=0.0;
					if(sizeAccount>=10) comision=10.0;
					
					if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
					
					    cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
	    		        cuenta.setUpdateDate(new Date());
	    		        operationDto.setComision(comision);
	    		        
					}else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
						
						cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
		    		    cuenta.setUpdateDate(new Date());
		    		    operationDto.setComision(comision);
					}

	    		    return  clientCurrent.update(cuenta,cuenta.getId()).flatMap(account->{

	    		    	 return repo.save(convert.convertOperation(operationDto));
	    		    });
	    		   

				});
				
				
			
		   });
		
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveSavingsVip(OperationDto operationDto) {
		
		return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(sizeAccount->{
			
			LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+sizeAccount.toString());

			return clientSavingsVip.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
				
				Double comision=0.0;
				if(sizeAccount>=10) comision=10.0;
				
				if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
				
				    cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
    		        cuenta.setUpdateDate(new Date());
    		        operationDto.setComision(comision);
    		        
				}else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
					
					cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
	    		    cuenta.setUpdateDate(new Date());
	    		    operationDto.setComision(comision);
				}

    		    return  clientSavingsVip.update(cuenta,cuenta.getId()).flatMap(account->{

    		    	 return repo.save(convert.convertOperation(operationDto));
    		    });
    		   

			});
			
			
		
	   });
	
	
	}
	
	
	@Override
	public Mono<ManageOperation> saveCurrentVip(OperationDto operationDto) {
		
     return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(sizeAccount->{
			
			LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+sizeAccount.toString());

			return clientCurrentVip.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
				
				Double comision=0.0;
				if(sizeAccount>=10) comision=10.0;
				
				if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
				
				    cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
    		        cuenta.setUpdateDate(new Date());
    		        operationDto.setComision(comision);
    		        
				}else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
					
					cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
	    		    cuenta.setUpdateDate(new Date());
	    		    operationDto.setComision(comision);
				}

    		    return  clientCurrentVip.update(cuenta,cuenta.getId()).flatMap(account->{

    		    	 return repo.save(convert.convertOperation(operationDto));
    		    });
    		   

			});
			
			
		
	   });
	
	}
	
	@Override
	public Mono<ManageOperation> savePyme(OperationDto operationDto) {
		
       return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(sizeAccount->{
			
			LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+sizeAccount.toString());

			return clientPyme.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
				
				Double comision=0.0;
				if(sizeAccount>=10) comision=10.0;
				
				if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
				
				    cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
    		        cuenta.setUpdateDate(new Date());
    		        operationDto.setComision(comision);
    		        
				}else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
					
					cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
	    		    cuenta.setUpdateDate(new Date());
	    		    operationDto.setComision(comision);
				}

    		    return  clientPyme.update(cuenta,cuenta.getId()).flatMap(account->{

    		    	 return repo.save(convert.convertOperation(operationDto));
    		    });
    		   

			});
			
			
		
	   });
	
	}
	
	@Override
	public Mono<ManageOperation> saveCorporative(OperationDto operationDto) {
		
        return repo.findByNumberAccount(operationDto.getNumberAccount()).count().flatMap(sizeAccount->{
			
			LOGGER.info("Cantidad_Operaciones_Cuenta:--->"+sizeAccount.toString());

			return clientCorporative.findByNumAccount(operationDto.getNumberAccount()).flatMap(cuenta->{
				
				Double comision=0.0;
				if(sizeAccount>=10) comision=10.0;
				
				if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.debito)) {
				
				    cuenta.setBalance((cuenta.getBalance()-operationDto.getAmountOperation())-comision);
    		        cuenta.setUpdateDate(new Date());
    		        operationDto.setComision(comision);
    		        
				}else if(operationDto.getTypeOperation().trim().toUpperCase().equals(TypeOperation.abono)) {
					
					cuenta.setBalance((cuenta.getBalance()+operationDto.getAmountOperation())-comision);
	    		    cuenta.setUpdateDate(new Date());
	    		    operationDto.setComision(comision);
				}

    		    return  clientCorporative.update(cuenta,cuenta.getId()).flatMap(account->{

    		    	 return repo.save(convert.convertOperation(operationDto));
    		    });

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
