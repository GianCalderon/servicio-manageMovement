package com.springboot.manageOperation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SearchDate {
	
	private String numberAccount;
	private Date inicio;
	private Date fin;

}
