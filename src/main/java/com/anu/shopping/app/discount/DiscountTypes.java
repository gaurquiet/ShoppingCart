package com.anu.shopping.app.discount;

public enum DiscountTypes {
	PRODUCT_FREE_ON_ANOTHER(true),
	SINGLE_PRODUCT_PERCENTAGE(true),
	NONE(true);
	
	private boolean isAllowedWithOtherDiscounts;
	public boolean isAllowedWithOtherDiscounts(){
		return isAllowedWithOtherDiscounts;
	}
	private DiscountTypes(boolean isAllowedWithOtherDiscounts) {
		this.isAllowedWithOtherDiscounts = isAllowedWithOtherDiscounts;
	}
}
