package com.anu.shopping.app.discount;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.QuantityPrice;
import com.anu.shopping.app.model.ShoppingCart;

public class SingleProductPercentageDiscount implements Discount {
	
	private Item primaryItem;
	private int discountPercentage;
	
	public SingleProductPercentageDiscount(Builder builder) {
		this.primaryItem = builder.primaryItem;
		this.discountPercentage = builder.discountPercentage;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}

	@Override
	public DiscountTypes getType() {
		return DiscountTypes.SINGLE_PRODUCT_PERCENTAGE;
	}

	@Override
	public DiscountApplied apply(ShoppingCart cart) {
		double discount;
		Optional<Pair<Item, QuantityPrice>> primary = cart.getItems().stream()
		.filter(pair -> pair.getLeft().getItemId() == primaryItem.getItemId())
		.filter(Objects::nonNull)
		.findFirst();
		if(primary.isPresent()){
			discount = primary.get().getRight().getPrice() * primary.get().getRight().getQuantity() * discountPercentage/100.0;
			return new DiscountApplied(getType(), discount);
		}
		return new DiscountApplied(DiscountTypes.NONE, 0.0);
	}
	
	public static class Builder{
		private Item primaryItem;
		private int discountPercentage;
		
		private Builder(){}
		public Builder withPrimaryItem(Item item){
			this.primaryItem = item;
			return this;
		}
	
		
		public Builder withPercentageDiscount(int percentage){
			this.discountPercentage = percentage;
			return this;
		}
		
		public SingleProductPercentageDiscount build(){
			return new SingleProductPercentageDiscount(this);
		}
	}


}
