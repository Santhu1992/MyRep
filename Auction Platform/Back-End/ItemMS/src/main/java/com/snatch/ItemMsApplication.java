package com.snatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemMsApplication.class, args);
	}

}
