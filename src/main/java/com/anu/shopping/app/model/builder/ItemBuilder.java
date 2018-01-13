package com.anu.shopping.app.model.builder;

import com.anu.shopping.app.model.Item;

public class ItemBuilder {
	
	private int itemId;
	private String itemName;
	private String unit;
	
	public static final ItemBuilder newInstance(){
		return new ItemBuilder();
	}
	
	private ItemBuilder(){}
	
	public ItemBuilder withItemId(int itemId){
		this.itemId = itemId;
		return this;
	}
	
	public ItemBuilder withItemName(String itemName){
		this.itemName = itemName;
		return this;
	}
	
	public ItemBuilder withUnit(String unit){
		this.unit = unit;
		return this;
	}
	
	public Item build(){
		return new Item(this.itemName, this.unit);
	}

}
