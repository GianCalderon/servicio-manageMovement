package com.springboot.manageOperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioManageOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioManageOperationApplication.class, args);
	}

}
