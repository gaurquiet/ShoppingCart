package com.anu.shopping.app.discount;

import com.anu.shopping.app.model.ShoppingCart;

public interface Discount {
	public DiscountTypes getType();
	public DiscountApplied apply(ShoppingCart cart);
}
