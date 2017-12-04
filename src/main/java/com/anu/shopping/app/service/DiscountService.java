package com.anu.shopping.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.model.ShoppingCart;

public class DiscountService {
	private List<Discount> discounts = new ArrayList<>();;
	
	public DiscountService(Discount ... discounts){
		this.discounts.addAll(CollectionUtils.arrayToList(discounts));
	}
	
	public DiscountService(List<Discount> discounts){
		this.discounts.addAll(discounts);
	}
	
	public ShoppingCart applyDiscounts(ShoppingCart cart){
		discounts.stream().forEach(d -> cart.addDiscount(d.apply(cart)));
		return cart;
	}
}
