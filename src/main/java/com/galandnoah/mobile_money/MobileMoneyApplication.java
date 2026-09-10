package com.galandnoah.mobile_money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MobileMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileMoneyApplication.class, args);
	}

}
