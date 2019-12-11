package com.springboot.manageMovement.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection ="manageMovements")
public class ManageMovement {
	
	@Id
	private String id;
	

}
