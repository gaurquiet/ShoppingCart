package com.anu.shopping.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.discount.DiscountApplied;


public class ShoppingCart {

	private int cartId;
	private List<Pair<Item, QuantityPrice>> items = new ArrayList<>();
	private List<DiscountApplied> allAppliedDiscounts = new ArrayList<>();
	private double totalPrice;	
	private double totalDiscount;
	private double finalPrice;


	public List<DiscountApplied> getAllAppliedDiscounts() {
		return allAppliedDiscounts;
	}
	public void setAllAppliedDiscounts(List<DiscountApplied> allAppliedDiscounts) {
		this.allAppliedDiscounts = allAppliedDiscounts;
	}
	
	public void addDiscount(DiscountApplied discount){
		allAppliedDiscounts.add(discount);
	}
	
	public double getTotalPrice() {
		totalPrice = items.stream()
				.filter(Objects::nonNull)
				.map(pair -> pair.getRight())
				.mapToDouble(qp -> qp.getPrice() * qp.getQuantity())
				.sum();
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalDiscount() {
		totalDiscount = allAppliedDiscounts.stream()
				.filter(Objects::nonNull)
				.mapToDouble(d -> d.getSavings())
				.sum();
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getFinalPrice() {
		finalPrice = totalPrice - totalDiscount;
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
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
