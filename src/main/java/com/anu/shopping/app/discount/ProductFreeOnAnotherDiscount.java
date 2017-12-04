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

}
