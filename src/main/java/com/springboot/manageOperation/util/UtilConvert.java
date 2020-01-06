package com.springboot.manageOperation.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.manageOperation.document.ManageOperation;
import com.springboot.manageOperation.dto.OperationDto;

@Component
public class UtilConvert {
	
	
	public ManageOperation convertOperation(OperationDto operationDto) {
		
		ManageOperation manageOperation=new ManageOperation();
		
		manageOperation.setNumberAccount(operationDto.getNumberAccount());
		manageOperation.setNumOperation(String.valueOf((int)(Math.random()*999+1)));
		manageOperation.setTypeOperation(operationDto.getTypeOperation());
		manageOperation.setAmount(operationDto.getAmountOperation());
		manageOperation.setCommission(operationDto.getComision());
		manageOperation.setDateOperation(new Date());
		

		
		return manageOperation;
		
		
	}

}
