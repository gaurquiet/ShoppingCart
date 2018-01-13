package com.anu.shopping.app.model.builder;

import com.anu.shopping.app.model.ShoppingCart;

public class ShoppingCartBuilder {
	private int cartId;
	
	public static ShoppingCartBuilder newInstance(){
		return new ShoppingCartBuilder();
	}
	
	private ShoppingCartBuilder(){}
	
	public ShoppingCartBuilder withCartId(int cartId){
		this.cartId = cartId;
		return this;
	}
	
	public ShoppingCart build(){
		ShoppingCart cart = new ShoppingCart();
		cart.setCartId(this.cartId);
		return cart;
	}

}
