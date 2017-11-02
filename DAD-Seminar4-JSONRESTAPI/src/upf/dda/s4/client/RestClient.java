package upf.dda.s4.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import upf.dda.s4.data.Item;


public class RestClient {

	public static void main(String[] args) {
		
		// Items
		upf.dda.s4.data.Item item1 = new upf.dda.s4.data.Item("iPhone6", 699, 10);
		Item item2 = new Item("iPhone5", 399, 500);
		
		Client client = ClientBuilder.newClient();
		WebTarget targetAdd = client.target("http://localhost:15000").path("item/add");
		
		// Add items
		Item response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
		.post(Entity.entity(item1, MediaType.APPLICATION_JSON), Item.class);
		Item response2 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
		.post(Entity.entity(item2, MediaType.APPLICATION_JSON), Item.class);
		
		WebTarget targetGet = client.target("http://localhost:15000").path("item/get/iPhone6");
		
		// Get item by name
		Item item = targetGet.request(
				MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {});
		System.out.println("Item: " + item);
		
		// Get all items here….
		WebTarget targetGetAll = client.target("http://localhost:15000").path("item/list");
		String allItems = targetGetAll.request(
				MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
		System.out.println("All items: " + allItems);
		
		
		//Delete iPhone5 and get all items
		WebTarget targetDelete = client.target("http://localhost:15000").path("item/delete/iPhone5");
		targetDelete.request(MediaType.APPLICATION_JSON_TYPE).delete();
		
		allItems = targetGetAll.request(
				MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
		System.out.println("All items: " + allItems);
		
		//Delete all and get all items
		WebTarget targetDeleteAll = client.target("http://localhost:15000").path("item/delete/all");
		targetDeleteAll.request(MediaType.APPLICATION_JSON_TYPE).delete();
		
		allItems = targetGetAll.request(
				MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
		System.out.println("All items: " + allItems);
		
				
	}
	
}
