package com.anu.shopping.app;

import org.springframework.web.bind.annotation.RestController;

import com.anu.shopping.app.dbservice.DbService;
import com.anu.shopping.app.model.InventoryItem;
import com.anu.shopping.app.model.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ShoppingCalculatorController {
	
	@Autowired
	private DbService dbService;
    
    @RequestMapping(path = "/", method=RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(path = "/items", method=RequestMethod.GET)
    public List<Item> getAllItems(){
    	return dbService.getAllItems();
    }
    
    @RequestMapping(path = "/item/{itemId}", method=RequestMethod.GET)
    public Item getItem(@RequestParam int itemId){
    	return dbService.getItemById(itemId);
    }
    
    @RequestMapping(path = "/inventory/{itemId}", method=RequestMethod.GET)
    public InventoryItem getInventoryById(@RequestParam int itemId){
    	return dbService.getInventoryById(itemId).get();
    }
    
    @RequestMapping(path = "/inventory", method=RequestMethod.GET)
    public List<InventoryItem> getInventory(){
    	return dbService.getInventory();
    }
    
    
    
}
