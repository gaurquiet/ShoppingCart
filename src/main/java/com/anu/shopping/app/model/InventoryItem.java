package com.anu.shopping.app.model;

public class InventoryItem {
	private int availableQuantity;
	private double pricePerUnit;
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public InventoryItem(int availableQuantity, double pricePerUnit) {
		super();
		this.availableQuantity = availableQuantity;
		this.pricePerUnit = pricePerUnit;
	}
}
