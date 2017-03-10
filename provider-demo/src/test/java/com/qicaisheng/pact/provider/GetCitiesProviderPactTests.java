package com.qicaisheng.pact.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restdriver.clientdriver.ClientDriverRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;

@RunWith(PactRunner.class)
@Provider("provider_demo")
@PactFolder("pacts")
public class GetCitiesProviderPactTests {
    @ClassRule
    public static final ClientDriverRule embeddedService = new ClientDriverRule(8090);

    @State("cites on the provider demo")
    public void toDefaultState() {
    }

    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget(8090);


    @Before
    public void before() throws JsonProcessingException {
        ProviderDemoApplication providerDemoApplication = new ProviderDemoApplication();
        List<City> cities = providerDemoApplication.getCities();
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(cities);

        embeddedService.addExpectation(
                onRequestTo("/cities"), giveResponse(body, "application/json;charset=utf-8")
        );
    }
}
