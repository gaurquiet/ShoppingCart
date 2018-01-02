package com.anu.shopping.app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockedControllerTest {
	
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
	      .apply(documentationConfiguration(this.restDocumentation))
	      .alwaysDo(document("{method-name}/{step}/"))
	      .build();
	}

	
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
