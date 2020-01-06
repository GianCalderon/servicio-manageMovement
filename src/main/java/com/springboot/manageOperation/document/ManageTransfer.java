package com.springboot.manageOperation.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection ="Transferencias-Cuentas")
public class ManageTransfer {
	
	private String accountOrigin;
	private String accountDestination;
	private Double transferAmount;

}
