package com.anu.shopping.app.dbservice;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.anu.shopping.app.discount.Discount;
import com.anu.shopping.app.discount.DiscountTypes;
import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.ShoppingCart;

public interface DbService {
	public int addItemInSystem(Item item);
	public void addItemInInventoryByItemId(int itemId, int quantity, double d);
	public void addItemInInventoryByName(String itemName, int quantity, double d);
	public Optional<Item> getItemById(int id);
	public Optional<Item> getItemByName(String name);
	public List<Item> getAllItems();
	public Optional<InventoryItem> getInventoryById(int id);
	public List<InventoryItem> getInventory();
	public ShoppingCart addNewCart(ShoppingCart cart);
	public ShoppingCart addItemInCart(int cartId, int itemId, int quantity);
	public Optional<ShoppingCart> getCartById(int cartId);
	public void addDiscount(Discount discount);
	public List<Discount> getDiscountByType(DiscountTypes type);
	public List<Discount> getAllDiscounts();
}

