package com.springboot.manageMovement.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonalDto {

	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;
	private List<String> idCuentas;



}
