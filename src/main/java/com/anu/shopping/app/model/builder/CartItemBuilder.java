package com.anu.shopping.app.model.builder;

import com.anu.shopping.app.model.CartItem;

public class CartItemBuilder {
	
	private int cartId;
	private int itemId;
	private String itemName;
	private int quantity;
	
	public static final CartItemBuilder newInstance(){
		return new CartItemBuilder();
	}
	
	private CartItemBuilder(){}
	
	public CartItemBuilder withCartId(int cartId){
		this.cartId = cartId;
		return this;
	}
	
	public CartItemBuilder withItemId(int itemId){
		this.itemId = itemId;
		return this;
	}
	
	public CartItemBuilder withItemName(String itemName){
		this.itemName = itemName;
		return this;
	}
	
	public CartItemBuilder withQuanity(int quantity){
		this.quantity = quantity;
		return this;
	}
	
	public CartItem build(){
		CartItem item = new CartItem();
		item.setCartId(this.cartId);
		item.setItemId(this.itemId);
		item.setItemName(this.itemName);
		item.setQuantity(this.quantity);
		return item;
	}

}
