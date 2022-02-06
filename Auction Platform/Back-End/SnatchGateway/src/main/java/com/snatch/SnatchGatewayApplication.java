package com.snatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SnatchGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnatchGatewayApplication.class, args);
	}

}
