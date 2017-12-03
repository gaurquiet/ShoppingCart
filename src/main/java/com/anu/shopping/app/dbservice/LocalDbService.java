package com.anu.shopping.app.dbservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;

public class LocalDbService implements DbService{
	private Map<Integer, Item> items = new HashMap<>();
	private int itemCounter = 0;
	private List<Pair<Item, InventoryItem>> inventory = new ArrayList<>();

	public int addItemInSystem(Item item){
		itemCounter++;
		item.setItemId(itemCounter);
		items.put(itemCounter, item);
		return itemCounter;
	}

	public void addItemInInventoryByItemId(int itemId, int quantity, double price){
		Optional<Pair<Item, InventoryItem>> item = inventory.stream()
		.filter(p -> p.getLeft().getItemId() == itemId)
		.findFirst();
		
		item.ifPresent(pair -> {
			pair.setValue(new InventoryItem(pair.getRight().getAvailableQuantity() + quantity, price));
		});
		
		if(!item.isPresent()){
			inventory.add(Pair.of(items.get(itemId), new InventoryItem(quantity, price)));
		}
	}

	public Item getItemById(int id){
		return items.get(id); 
	}

	public List<Item> getAllItems(){
		return items.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Pair<Item, InventoryItem>> getInventoryById(int itemId) {
		return inventory.stream()
				.filter(p -> p.getLeft().getItemId() == itemId)
				.findFirst();
	}

	@Override
	public List<Pair<Item, InventoryItem>> getInventory() {
		return inventory;
	}

	@Override
	public void addItemInInventoryByName(String itemName, int quantity, double d) {
		Optional<Pair<Item, InventoryItem>> item = inventory.stream()
		.filter(p -> (p != null && p.getLeft() != null && p.getLeft().getItemName() == itemName))
		.findFirst();
		
		item.ifPresent(pair -> {
			pair.setValue(new InventoryItem(pair.getRight().getAvailableQuantity() + quantity, d));
		});
		
		int itemId = items.values().stream().filter(i -> i.getItemName().equals(itemName)).findFirst().get().getItemId();
		
		if(!item.isPresent()){
			inventory.add(Pair.of(items.get(itemId), new InventoryItem(quantity, d)));
		}
		
	}
	
	
}
