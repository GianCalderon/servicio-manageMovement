package com.springboot.manageOperation.document;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection ="manageOperation")
public class ManageOperation {
	
	@Id
	private String id;
	
	@NotNull(message = "Operation numDoc must not be null")
	@NotEmpty(message = "numDoc may not be empty")
	private String numDoc;
	
	@NotNull(message = "Operation numAccount must not be null")
	@NotEmpty(message = "numAccount may not be empty")
	private String numAccount;
	
	@NotNull(message = "Operation numOperation must not be null")
	@NotEmpty(message = "numOperation may not be empty")
	private String numOperation;
	
	@NotNull(message = "Operation typeOperation must not be null")
	@NotEmpty(message = "typeOperation may not be empty")
	private String typeOperation;
	
	@NotNull(message = "Operation amount must not be null")
	@Min(1)
	@Max(3000)
	private Double amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOperation;

}
