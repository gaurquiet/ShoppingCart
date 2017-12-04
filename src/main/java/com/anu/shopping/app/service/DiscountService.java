package com.anu.shopping.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.model.Order;
import com.anu.shopping.app.model.ShoppingCart;

public class DiscountService {
	private List<Discount> discounts = new ArrayList<>();;
	
	public DiscountService(Discount ... discounts){
		this.discounts.addAll(CollectionUtils.arrayToList(discounts));
	}
	
	public DiscountService(List<Discount> discounts){
		this.discounts.addAll(discounts);
	}
	
	public Order applyDiscounts(ShoppingCart cart){
		Order order = new Order();
		order.setCart(cart);
		discounts.stream().forEach(d -> order.addDiscount(d.apply(cart)));
		return order;
	}
}
