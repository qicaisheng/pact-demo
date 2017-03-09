package com.qicaisheng.pact.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
public class ConsumerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerDemoApplication.class, args);
	}

	@RequestMapping("/")
	public String consumerDemoPage() {
		return "This is consumer demo page.";
	}

	@RequestMapping("/cities")
	public List<City> getCites() {

		ParameterizedTypeReference<List<City>> parameterizedTypeReference = new ParameterizedTypeReference<List<City>>() {};
		return new RestTemplate().exchange("http://localhost:8090/cities", HttpMethod.GET, null, parameterizedTypeReference).getBody();
	}
}
