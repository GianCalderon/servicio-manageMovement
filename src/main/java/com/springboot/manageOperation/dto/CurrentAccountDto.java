
package com.springboot.manageOperation.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CurrentAccountDto {
	
	

	private String id;
	private String name;
	
	private String numberAccount;
	
	private Double tea;
	
	private String state;
	
	private Double balance;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	private List<String> idOperation;

	public CurrentAccountDto(String name, String numberAccount, Double tea, String state, Double balance,
			Date createDate, Date updateDate, List<String> idOperation) {
		super();
	
		this.name = name;
		this.numberAccount = numberAccount;
		this.tea = tea;
		this.state = state;
		this.balance = balance;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.idOperation = idOperation;
	}

	public CurrentAccountDto() {
		
	} 
	


}
