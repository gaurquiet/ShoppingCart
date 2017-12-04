package com.anu.shopping.app.util;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.discount.ProductFreeOnAnotherDiscount;
import com.anu.shopping.app.discount.SingleProductPercentageDiscount;
import com.anu.shopping.app.model.Item;

public class StaticDataLoader {
	
	public static void loadAllData(DbService dbService){
		loadItemData(dbService);
		loadInventoryData(dbService);
	}
	
	private static void loadItemData(DbService dbService){
		dbService.addItemInSystem(new Item("Soup", "tin"));
		dbService.addItemInSystem(new Item("Bread", "loaf"));
		dbService.addItemInSystem(new Item("Milk", "bottle"));
		dbService.addItemInSystem(new Item("Apple", "bag"));
	}
	
	private static void loadInventoryData(DbService dbService){
		dbService.addItemInInventoryByName("Soup", 5, 0.65);
		dbService.addItemInInventoryByName("Bread", 5, 0.80);
		dbService.addItemInInventoryByName("Milk", 5, 1.30);
		dbService.addItemInInventoryByName("Apple", 5, 1.00);
	}
	
	public static Discount loadProductFreeOnAnotherDiscount(DbService dbService){
		Discount discount = ProductFreeOnAnotherDiscount.newBuilder()
				.withPrimaryItem(dbService.getItemByName("Soup").get())
				.withEligibleQuantity(2)
				.withFreeItem(dbService.getItemByName("Bread").get())
				.withDiscountPercentageOnSecond(50.0f)
				.build();
		dbService.addDiscount(discount);
		return discount;
	}
	
	public static Discount loadSingleProductPercentageDiscount(DbService dbService){
		Discount discount = SingleProductPercentageDiscount.newBuilder()
				.withPrimaryItem(dbService.getItemByName("Apple").get())
				.withPercentageDiscount(10)
				.build();
		dbService.addDiscount(discount);
		return discount;
	}
	


}
