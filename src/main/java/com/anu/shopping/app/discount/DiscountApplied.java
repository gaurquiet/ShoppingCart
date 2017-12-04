package com.anu.shopping.app.discount;

public class DiscountApplied {
	private DiscountTypes type;
	private double savings;
	public DiscountTypes getType() {
		return type;
	}
	public void setType(DiscountTypes type) {
		this.type = type;
	}
	public double getSavings() {
		return savings;
	}
	public void setSavings(double savings) {
		this.savings = savings;
	}
	public DiscountApplied(DiscountTypes type, double savings) {
		super();
		this.type = type;
		this.savings = savings;
	}
	
	
}
