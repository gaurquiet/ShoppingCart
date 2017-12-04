package com.anu.shopping.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.model.CartItem;
import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.ShoppingCart;

public class ShoppingService {
	@Autowired
	private DbService dbService;
	@Autowired
	private DiscountService discountService;
	
	public ShoppingCart startNewCart(){
		return dbService.addNewCart(new ShoppingCart());
	}
	
	public ShoppingCart addItemInCart(CartItem cartItem) throws Exception{
		Item item = getItemByIdOrName(cartItem.getItemId(), cartItem.getItemName());
		return dbService.addItemInCart(cartItem.getCartId(), item.getItemId(), cartItem.getQuantity());
	}
	
	public Item getItemByIdOrName(int itemId, String name) throws Exception{
		if((null == name || name.trim().equals("")) && itemId == 0){
			throw new Exception("Item name or item Id, one has to be passed");
		}
		Optional<Item> item = Optional.empty();
		if(itemId == 0){
			 item = dbService.getItemByName(name);
		} else {
			item = dbService.getItemById(itemId);
		}
		if(!item.isPresent()){
			throw new Exception("Item not present.");
		}
		return item.get();
	}
	
	public ShoppingCart getCartById(int cartId) throws Exception{
		if(cartId == 0){
			throw new Exception("Cart id wrong.");
		}
		Optional<ShoppingCart> cart = dbService.getCartById(cartId);
		if(!cart.isPresent()){
			throw new Exception("cart not present.");
		}
		return cart.get();
	}
	
	public ShoppingCart applyDiscountOnCart(int cartId) throws Exception{
		return discountService.applyDiscounts(getCartById(cartId));
	}
}
