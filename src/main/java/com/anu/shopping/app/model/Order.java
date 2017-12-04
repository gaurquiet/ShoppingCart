package com.anu.shopping.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.anu.shopping.app.discount.DiscountApplied;

public class Order {
	private ShoppingCart cart;
	private List<DiscountApplied> allAppliedDiscounts = new ArrayList<>();
	private double totalDiscount;
	private double finalPrice;
	
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getFinalPrice() {
		finalPrice = cart.getShoppingValue() - totalDiscount;
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	public List<DiscountApplied> getAllAppliedDiscounts() {
		return allAppliedDiscounts;
	}
	public void setAllAppliedDiscounts(List<DiscountApplied> allAppliedDiscounts) {
		this.allAppliedDiscounts = allAppliedDiscounts;
	}
	
	public void addDiscount(DiscountApplied discount){
		allAppliedDiscounts.add(discount);
	}
	public double getTotalDiscount() {
		totalDiscount = allAppliedDiscounts.stream()
				.filter(Objects::nonNull)
				.mapToDouble(d -> d.getSavings())
				.sum();
		return totalDiscount;
	}
}
