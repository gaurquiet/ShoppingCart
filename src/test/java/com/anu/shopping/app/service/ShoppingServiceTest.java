package com.anu.shopping.app.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.dbservice.LocalDbService;
import com.anu.shopping.app.model.ShoppingCart;

public class ShoppingServiceTest {
	@InjectMocks
	private ShoppingService subject;
	@Mock
	private LocalDbService dbService;
	
	//@Before
	public void setUp(){
		subject = new ShoppingService();
		//dbService = mock(LocalDbService.class);
		//Whitebox.setInternalState(subject, "dbService", dbService);
		ShoppingCart newCart = mock(ShoppingCart.class);
		when(dbService.addNewCart(any(ShoppingCart.class))).thenReturn(newCart);
	}

	//@Test
	public void testStartNewCart() {
		ShoppingCart cart = subject.startNewCart();
		assertNotNull(cart);
	}

	//@Test
	public void testAddItemInCart() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetItemByIdOrName() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetCartById() {
		fail("Not yet implemented");
	}

	//@Test
	public void testApplyDiscountOnCart() {
		fail("Not yet implemented");
	}

}
