package com.anu.shopping.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockedControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
		this.mockMvc
		.perform(get("/"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Greetings from Spring Boot!")));
    }
	
	//@Test
    public void startShoppingShouldReturnAShoppingCart() throws Exception {
		this.mockMvc
		.perform(get("/cart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Greetings from Spring Boot!")));
    }

}
