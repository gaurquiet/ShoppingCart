package com.anu.shopping.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RunningControllerTest {
	
	@Autowired
	private ShoppingCalculatorController myController;
	
	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
    public void contextLoads() throws Exception {
		assertThat(myController).isNotNull();
    }
	
	@Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Greetings from Spring Boot!");
    }
	
	@Test
    public void startShoppingShouldReturnAShoppingCart() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cart",
                String.class)).contains("Greetings from Spring Boot!");
    }
}
