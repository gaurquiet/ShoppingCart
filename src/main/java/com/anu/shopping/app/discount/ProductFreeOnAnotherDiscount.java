package com.anu.shopping.app.discount;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.QuantityPrice;
import com.anu.shopping.app.model.ShoppingCart;

public class ProductFreeOnAnotherDiscount implements Discount {
	private Item primaryItem;
	private int eligibleQuantity;
	private Item freeItem;
	private float discountPercentageOnSecond;
	
	

	public ProductFreeOnAnotherDiscount(Builder builder) {
		super();
		this.primaryItem = builder.primaryItem;
		this.eligibleQuantity = builder.eligibleQuantity;
		this.freeItem = builder.freeItem;
		this.discountPercentageOnSecond = builder.discountPercentageOnSecond;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}

	public Item getPrimaryItem() {
		return primaryItem;
	}

	public void setPrimaryItem(Item primaryItem) {
		this.primaryItem = primaryItem;
	}

	public int getEligibleQuantity() {
		return eligibleQuantity;
	}

	public void setEligibleQuantity(int eligibleQuantity) {
		this.eligibleQuantity = eligibleQuantity;
	}

	public Item getFreeItem() {
		return freeItem;
	}

	public void setFreeItem(Item freeItem) {
		this.freeItem = freeItem;
	}

	public float getDiscountPercentageOnSecond() {
		return discountPercentageOnSecond;
	}

	public void setDiscountPercentageOnSecond(float discountPercentageOnSecond) {
		this.discountPercentageOnSecond = discountPercentageOnSecond;
	}

	@Override
	public String toString() {
		return "ProductFreeOnAnotherDiscount [primaryItem=" + primaryItem + ", eligibleQuantity=" + eligibleQuantity
				+ ", freeItem=" + freeItem + ", discountPercentageOnSecond=" + discountPercentageOnSecond + "]";
	}

	@Override
	public DiscountApplied apply(ShoppingCart cart) {
		double discount;

		Optional<Pair<Item, QuantityPrice>> primary = cart.getItems().stream()
		.filter(pair -> (pair.getLeft().getItemId() == primaryItem.getItemId() && pair.getRight().getQuantity() >= eligibleQuantity))
		.filter(Objects::nonNull)
		.findFirst();
		
		if(primary.isPresent()){
			Optional<Pair<Item, QuantityPrice>> free = cart.getItems().stream()
					.filter(pair -> pair.getLeft().getItemId() == freeItem.getItemId())
					.findFirst();
			if(free.isPresent()){
				discount = free.get().getRight().getPrice() * free.get().getRight().getQuantity() * discountPercentageOnSecond /100;
				return new DiscountApplied(getType(), discount);
			}
		}
		return new DiscountApplied(DiscountTypes.NONE, 0.0);
	}

	@Override
	public DiscountTypes getType() {
		return DiscountTypes.PRODUCT_FREE_ON_ANOTHER;
	}

	public static class Builder{
		private Item primaryItem;
		private int eligibleQuantity;
		private Item freeItem;
		private float discountPercentageOnSecond;
		
		private Builder(){}
		public Builder withPrimaryItem(Item item){
			this.primaryItem = item;
			return this;
		}
		
		public Builder withEligibleQuantity(int quantity){
			this.eligibleQuantity = quantity;
			return this;
		}
		
		public Builder withFreeItem(Item item){
			this.freeItem = item;
			return this;
		}
		
		public Builder withDiscountPercentageOnSecond(float percentage){
			this.discountPercentageOnSecond = percentage;
			return this;
		}
		
		public ProductFreeOnAnotherDiscount build(){
			return new ProductFreeOnAnotherDiscount(this);
		}
	}
}
