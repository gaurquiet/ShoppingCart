package com.anu.shopping.app.dbservice;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;

public interface DbService {
	public int addItemInSystem(Item item);
	public void addItemInInventoryByItemId(int itemId, int quantity, double d);
	public void addItemInInventoryByName(String itemName, int quantity, double d);
	public Item getItemById(int id);
	public List<Item> getAllItems();
	public Optional<Pair<Item, InventoryItem>> getInventoryById(int id);
	public List<Pair<Item, InventoryItem>> getInventory();
}
