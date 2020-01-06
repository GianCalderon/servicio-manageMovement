package com.springboot.manageOperation.dto;

import lombok.Data;

@Data
public class OperationDto {
	
	private String numberAccount;
	private String typeOperation;
	private Double amountOperation;
	
	private Double comision;
	private String numDoc;

}
