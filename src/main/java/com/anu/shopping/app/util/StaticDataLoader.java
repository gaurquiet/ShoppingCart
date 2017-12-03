package com.anu.shopping.app.util;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.model.Item;

public class StaticDataLoader {
	
	public static void loadItemData(DbService dbService){
		dbService.addItemInSystem(new Item("Soup", "tin"));
		dbService.addItemInSystem(new Item("Bread", "loaf"));
		dbService.addItemInSystem(new Item("Milk", "bottle"));
		dbService.addItemInSystem(new Item("Apple", "bag"));
	}
	
	public static void loadInventoryData(DbService dbService){
		loadItemData(dbService);
		dbService.addItemInInventoryByName("Soup", 5, 0.65);
		dbService.addItemInInventoryByName("Bread", 5, 0.80);
		dbService.addItemInInventoryByName("Milk", 5, 1.30);
		dbService.addItemInInventoryByName("Apple", 5, 1.00);
	}

}
