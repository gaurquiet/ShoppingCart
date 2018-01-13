package com.anu.shopping.app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.core.IsEqual;

import static org.hamcrest.collection.IsArrayWithSize.emptyArray;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.anu.shopping.app.dbservice.LocalDbService;
import com.anu.shopping.app.model.CartItem;
import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.ShoppingCart;
import com.anu.shopping.app.model.builder.CartItemBuilder;
import com.anu.shopping.app.model.builder.ItemBuilder;
import com.anu.shopping.app.model.builder.ShoppingCartBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	public void setUp() {
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

	@Test
	public void startShoppingShouldReturnAShoppingCart() throws Exception {
		this.mockMvc
		.perform(get("/cart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber());
	}

	@Test
	public void handleCartNotFound() throws Exception {
		this.mockMvc.perform(get("/cart/99"))
		.andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string(containsString("Cart with id 99 doesn't exist.")));
	}

	@Test
	public void getCartById() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		this.mockMvc
		.perform(get("/cart/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber())
		.andExpect(jsonPath("$.cartId").value(1));
	}

	@Test
	public void addAppleInCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		CartItem apple = CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Apple")
				.withQuanity(10).build();
		
		this.mockMvc
		.perform(post("/cart/1/item")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(apple))
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber())
		.andExpect(jsonPath("$.cartId").value(1))
		.andExpect(jsonPath("$.items").isArray());
		//.andExpect(jsonPath("$.items[0]").value(content().string(containsString("Apple"))));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();;
	}
	
	@Test
	public void addSoupInCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		
		this.mockMvc
		.perform(post("/cart/1/item")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(
				CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Soup")
				.withQuanity(2).build()))
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber())
		.andExpect(jsonPath("$.cartId").value(1))
		.andExpect(jsonPath("$.items").isArray());
		//.andExpect(jsonPath("$.items[0]").value(content().string(containsString("Apple"))));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();
	}
	
	@Test
	public void addBreadInCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		
		this.mockMvc
		.perform(post("/cart/1/item")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(
				CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Bread")
				.withQuanity(1).build()))
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber())
		.andExpect(jsonPath("$.cartId").value(1))
		.andExpect(jsonPath("$.items").isArray());
		//.andExpect(jsonPath("$.items[0]").value(content().string(containsString("Apple"))));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();
	}
	
	@Test
	public void addMilkInCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		
		this.mockMvc
		.perform(post("/cart/1/item")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(
				CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Milk")
				.withQuanity(10).build()))
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartId").isNumber())
		.andExpect(jsonPath("$.cartId").value(1))
		.andExpect(jsonPath("$.items").isArray());
		//.andExpect(jsonPath("$.items[0]").value(content().string(containsString("Apple"))));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();
	}
	
	@Test
	public void calculateDiscountOnEmptyCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		this.mockMvc
		.perform(get("/cart/1/applyDiscount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cart.cartId").isNumber())
		.andExpect(jsonPath("$.cart.cartId").value(1))
		.andExpect(jsonPath("$.cart.items").isArray())
		.andExpect(jsonPath("$.cart.items", hasSize(0)));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();
	}
	
	@Test
	public void calculateDiscountOnFilledCart() throws Exception {

		this.context.getBean(LocalDbService.class).addNewCart(ShoppingCartBuilder.newInstance().withCartId(1).build());
		
		CartItem apple = CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Apple")
				.withQuanity(10).build();
		
		CartItem milk = CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Milk")
				.withQuanity(10).build();
		
		CartItem soup = CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Soup")
				.withQuanity(2).build();
		
		CartItem bread = CartItemBuilder.newInstance()
				.withCartId(1)
				.withItemName("Bread")
				.withQuanity(10).build();
		this.context.getBean(LocalDbService.class).addItemInCart(apple.getCartId(), apple);
		this.context.getBean(LocalDbService.class).addItemInCart(milk.getCartId(), milk);
		this.context.getBean(LocalDbService.class).addItemInCart(soup.getCartId(), soup);
		this.context.getBean(LocalDbService.class).addItemInCart(bread.getCartId(), bread);
		
		this.mockMvc
		.perform(get("/cart/1/applyDiscount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cart.cartId").isNumber())
		.andExpect(jsonPath("$.cart.cartId").value(1))
		.andExpect(jsonPath("$.cart.items").isArray())
		.andExpect(jsonPath("$.cart.items", hasSize(4)));
		
		this.context.getBean(LocalDbService.class).removeAllCarts();
	}
	
	@Test
	public void getItemById() throws Exception {
		this.mockMvc.perform(get("/item/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.itemId").value("1"))
		.andExpect(jsonPath("$.itemName").value("Soup"));
	}
	
	@Test
	public void handleItemNotFound() throws Exception {
		this.mockMvc.perform(get("/item/99"))
		.andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string(containsString("Item with id 99 doesn't exist.")));
	}
	
	@Test
	public void getAllItems() throws Exception {
		this.mockMvc.perform(get("/items"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].itemId").value("1"))
		.andExpect(jsonPath("$[0].itemName").value("Soup"));
	}

}
