package com.anu.shopping.app.model;

public class InventoryItem {
	private Item item;
	private QuantityPrice quantityPrice;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public QuantityPrice getQuantityPrice() {
		return quantityPrice;
	}
	public void setQuantityPrice(QuantityPrice quantityPrice) {
		this.quantityPrice = quantityPrice;
	}
	public InventoryItem(Item item, QuantityPrice quantityPrice) {
		super();
		this.item = item;
		this.quantityPrice = quantityPrice;
	}
	public InventoryItem() {

	}
	
	
}
