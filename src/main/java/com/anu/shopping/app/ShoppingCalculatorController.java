package com.anu.shopping.app;

import org.springframework.web.bind.annotation.RestController;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.model.CartItem;
import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;
import com.anu.shopping.app.model.Order;
import com.anu.shopping.app.model.ShoppingCart;
import com.anu.shopping.app.service.ShoppingService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ShoppingCalculatorController {

	@Autowired
	private DbService dbService;
	@Autowired
	private ShoppingService shoppingService;

	@RequestMapping(path = "/", method=RequestMethod.GET)
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(path = "/items", method=RequestMethod.GET)
	public List<Item> getAllItems(){
		return dbService.getAllItems();
	}

	@RequestMapping(path = "/item/{itemId}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Integer itemId) throws Exception{
		return shoppingService.getItemByIdOrName(itemId, null);
	}

	@RequestMapping(path = "/inventory/{itemId}", method=RequestMethod.GET)
	public InventoryItem getInventoryById(@PathVariable Integer itemId){
		return dbService.getInventoryById(itemId).get();
	}

	@RequestMapping(path = "/inventory", method=RequestMethod.GET)
	public List<InventoryItem> getInventory(){
		return dbService.getInventory();
	}

	@RequestMapping(path = "/cart", method=RequestMethod.GET)
	public ShoppingCart startShopping(){
		return shoppingService.startNewCart();
	}
	
	@RequestMapping(path = "/cart/{cartId}", method=RequestMethod.GET)
	public ShoppingCart getCartById(@PathVariable Integer cartId) throws Exception{
		if(cartId == 0){
			throw new Exception("Please provide a valid cart id.");
		}
		return shoppingService.getCartById(cartId);
	}

	@RequestMapping(path = "/cart/{cartId}/item", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON)
	public ShoppingCart addItemInCart(@RequestBody CartItem cartItem, @PathVariable Integer cartId) throws Exception{
		
		if(cartItem == null || cartItem.getCartId() != cartId){
			throw new Exception("Invalid request");
		}
		return shoppingService.addItemInCart(cartItem);
	}
	
	@RequestMapping(path = "/cart/{cartId}/applyDiscount", method=RequestMethod.GET)
	public Order applyDiscountOnCart(@PathVariable Integer cartId) throws Exception{
		return shoppingService.applyDiscountOnCart(cartId);
	}

}
