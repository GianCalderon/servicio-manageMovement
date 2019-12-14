package com.springboot.manageOperation.document;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection ="manageOperation")
public class ManageOperation {
	
	@Id
	private String id;
	
	@NotBlank
	private String numDoc;
	
	@NotBlank
	private String numberAccount;
	
	@NotBlank
	private String numOperation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMovement;
	
	@NotBlank
	private String tipoMovement;
	
	@NotBlank
	private int amount;
}
