package com.anu.shopping.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.discount.Discount;


public class ShoppingCart {

	private int cartId;
	List<Pair<Item, QuantityPrice>> items = new ArrayList<>();
	List<Discount> allAppliedDiscounts;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<Pair<Item, QuantityPrice>> getItems() {
		return items;
	}
	public void setItems(List<Pair<Item, QuantityPrice>> items) {
		this.items = items;
	}

	public List addItemInCart(Item item, QuantityPrice quantityPrice){
		items.add(Pair.of(item, quantityPrice));
		return items;
	}

	public List removeItemFromCart(Item item){
		Optional<Pair<Item, QuantityPrice>> pair = items.stream()
				.filter(Objects::nonNull)
				.filter(p -> p.getLeft().getItemId() == item.getItemId())
				.findFirst();
		pair.ifPresent(p -> items.remove(p));
		return items;
	}

}
