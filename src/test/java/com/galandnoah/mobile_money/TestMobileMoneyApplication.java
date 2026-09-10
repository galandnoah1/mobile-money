package com.galandnoah.mobile_money;

import org.springframework.boot.SpringApplication;

public class TestMobileMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.from(MobileMoneyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
