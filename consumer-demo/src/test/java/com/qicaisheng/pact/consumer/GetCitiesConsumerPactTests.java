package com.qicaisheng.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GetCitiesConsumerPactTests {
    private List<City> cities = new ArrayList<>();

    public GetCitiesConsumerPactTests() {
        City beijing = new City("beijing", 2000);
        City shenzhen = new City("shenzhen", 1500);
        City shanghai = new City("shanghai", 1800);
        City hangzhou = new City("hangzhou", 1000);
        City wuhan = new City("wuhan", 800);
        cities = Arrays.asList(beijing, shenzhen, shanghai, hangzhou, wuhan);
    }

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("provider_demo", "localhost", 8090, this);

    @Pact(state = "cities_state", provider="provider_demo", consumer="consumer_demo")
    public PactFragment createFragment(PactDslWithProvider builder) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(cities);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=utf-8");

        return builder
            .given("contract for consumer getting cities")
            .uponReceiving("Get cities")
                .path("/cities")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
            .toFragment();
    }

    @Test
    @PactVerification("provider_demo")
    public void runTest() {
        List<City> citiesResponse = new ConsumerDemoApplication().getCites();

        assertEquals(cities.size(), citiesResponse.size());
    }
}
