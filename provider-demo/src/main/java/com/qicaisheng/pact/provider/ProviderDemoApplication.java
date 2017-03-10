package com.qicaisheng.pact.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class ProviderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderDemoApplication.class, args);
	}

	public ProviderDemoApplication() {
	}

	@RequestMapping(value = "/cities", produces = "pplication/json;charset=UTF-8")
	public List<City> getCities() {
		City beijing = new City("beijing", 2000);
		City shenzhen = new City("shenzhen", 1500);
		City shanghai = new City("shanghai", 1800);
		City hangzhou = new City("hangzhou", 1000);
		City wuhan = new City("wuhan", 800);

		return Arrays.asList(beijing, shenzhen, shanghai, hangzhou, wuhan);
	}

	@RequestMapping("/")
	public String providerDemoPage() {

		return "This is provider demo page.";
	}
}
