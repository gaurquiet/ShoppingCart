package com.anu.shopping.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

public class ShoppingCart {

	private int cartId;
	private List<Pair<Item, QuantityPrice>> items = new ArrayList<>();
	private double shoppingValue;	

	public double getShoppingValue() {
		shoppingValue = items.stream()
				.filter(Objects::nonNull)
				.map(pair -> pair.getRight())
				.mapToDouble(qp -> qp.getPrice() * qp.getQuantity())
				.sum();
		return shoppingValue;
	}
	public void setShoppingValue(double totalPrice) {
		this.shoppingValue = totalPrice;
	}

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
		return removeItemFromCart(item.getItemId());
	}
	
	public List removeItemFromCart(Optional<Pair<Item, QuantityPrice>> pair){
		return removeItemFromCart(pair.get().getLeft().getItemId());
	}
	
	public List removeItemFromCart(int itemId){
		 Object o = items.stream()
				.filter(Objects::nonNull)
				.filter(p -> p.getLeft().getItemId() == itemId)
				.findFirst().get();
		 items.remove(o);
		 return items;

	}

}
