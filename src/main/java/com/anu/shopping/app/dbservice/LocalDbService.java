package com.anu.shopping.app.dbservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.discount.DiscountTypes;
import com.anu.shopping.app.model.CartItem;
import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.QuantityPrice;
import com.anu.shopping.app.model.ShoppingCart;

public class LocalDbService implements DbService{
	private Map<Integer, Item> items = new HashMap<>();
	private int itemCounter = 0;
	private int cartCounter = 0;
	private List<InventoryItem> inventory = new ArrayList<>();
	private List<Discount> discounts = new ArrayList<>();
	private Map<Integer, ShoppingCart> allCarts = new HashMap<>();
	
	@Override
	public void addDiscount(Discount discount){
		discounts.add(discount);
	}
	
	@Override
	public List<Discount> getDiscountByType(DiscountTypes type){
		return discounts.stream()
		.filter(d -> d.getType().equals(type))
		.filter(Objects::nonNull)
		.collect(Collectors.toList());
	}
	
	@Override
	public List<Discount> getAllDiscounts(){
		return discounts.stream()
		.filter(Objects::nonNull)
		.collect(Collectors.toList());
	}
	
	@Override
	public ShoppingCart addNewCart(ShoppingCart cart){
		cartCounter++;
		cart.setCartId(cartCounter);
		allCarts.put(cartCounter,  cart);
		return cart;
	}
	
	@Override
	public ShoppingCart addItemInCart(int cartId, int itemId, int quantity){
		ShoppingCart cart = allCarts.get(cartId);
		if(cart == null){
			cart = addNewCart(new ShoppingCart());
		}
		double price = 0;
		
		if(getInventoryById(itemId).isPresent()){
			price = getInventoryById(itemId).get().getQuantityPrice().getPrice();
		}
		Optional<Pair<Item, QuantityPrice>> itemAlreadyThere = cart.getItems().stream()
				.filter(Objects::nonNull)
				.filter(pair -> Objects.nonNull(pair.getLeft()))
				.filter(anotherPair -> anotherPair.getLeft().getItemId() == itemId)
				.filter(oneMorePair -> Objects.nonNull(oneMorePair.getRight()))
				.findFirst();
		if(itemAlreadyThere.isPresent()){
			cart.removeItemFromCart(itemAlreadyThere);
			cart.addItemInCart(getItemById(itemId).get(), 
					new QuantityPrice(quantity + itemAlreadyThere.get().getRight().getQuantity(), price));
		} else {
			cart.addItemInCart(getItemById(itemId).get(), new QuantityPrice(quantity, price));
		}
		allCarts.put(cart.getCartId(), cart);
		return cart;
	}
	
	public ShoppingCart addItemInCart(int cartId, CartItem item){
		int itemId;
		if(item.getItemId() > 0){
			itemId = item.getItemId();
		} else {
			itemId = inventory.stream()
					.filter(i -> i.getItem().getItemName().equals(item.getItemName()))
					.map(i -> i.getItem().getItemId())
					.findFirst().get().intValue();
		}
		return addItemInCart(cartId, item.getItemId(), item.getQuantity());
	}

	@Override
	public int addItemInSystem(Item item){
		itemCounter++;
		item.setItemId(itemCounter);
		items.put(itemCounter, item);
		return itemCounter;
	}

	@Override
	public void addItemInInventoryByItemId(int itemId, int quantity, double price){
		Optional<InventoryItem> item = inventory.stream()
		.filter(p -> p.getItem().getItemId() == itemId)
		.findFirst();
		
		item.ifPresent(pair -> {
			pair.setQuantityPrice(new QuantityPrice(quantity, price));
		});
		
		if(!item.isPresent()){
			inventory.add(new InventoryItem(items.get(itemId), new QuantityPrice(quantity, price)));
		}
	}

	@Override
	public Optional<Item> getItemById(int id){
		return Optional.ofNullable(items.get(id)); 
	}
	
	public void removeItemFromCart(int itemId, int cartId){
		allCarts.get(cartId).removeItemFromCart(itemId);
	}
	
	public void removeCartById(int cartId){
		allCarts.remove(cartId);
	}
	
	public void removeAllCarts(){
		allCarts = new HashMap<>();
		cartCounter = 0;
		itemCounter = 0;
	}

	@Override
	public List<Item> getAllItems(){
		return items.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<InventoryItem> getInventoryById(int itemId) {
		return inventory.stream()
				.filter(p -> p.getItem().getItemId() == itemId)
				.findFirst();
	}

	@Override
	public List<InventoryItem> getInventory() {
		return inventory;
	}

	@Override
	public void addItemInInventoryByName(String itemName, int quantity, double price) {
		Optional<InventoryItem> item = inventory.stream()
		.filter(p -> (p != null && p.getItem() != null && p.getItem().getItemName() == itemName))
		.findFirst();
		
		item.ifPresent(invItem -> {
			invItem.setQuantityPrice(new QuantityPrice(quantity, price));
		});
		
		Optional<Item> itemByName = items.values().stream().filter(i -> i.getItemName().equals(itemName)).findFirst();
		
		if(!item.isPresent()){
			inventory.add(new InventoryItem(itemByName.get(), new QuantityPrice(quantity, price)));
		}
		
	}

	@Override
	public Optional<Item> getItemByName(String name) {
		return items.values().stream().filter(item -> item.getItemName().trim().equalsIgnoreCase(name.trim())).findFirst();
	}
	
	@Override
	public Optional<ShoppingCart> getCartById(int cartId){
		return Optional.ofNullable(allCarts.get(cartId));
	}
}
