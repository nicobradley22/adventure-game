package game;

/**
 * Inventory.java
 *
 * Class to define an object to hold and manipulate the player's inventory
 * of items.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Map; // for maps
import java.util.HashMap; // for maps
import java.util.Collections; // for unmodifiable collections

public class Inventory {
	
	/**
	 * A map of items in the inventory (maps item name to item object)
	 */
	private Map<String, Item> items;

	/**
	 * The maximum capacity of the inventory.
	 */
	private int capacity;

	/**
	 * Constructor.
	 * @param capacity The maximum capacity of the inventory.
	 */
	public Inventory(int capacity) {
		this.capacity = capacity;
		items = new HashMap<String, Item>(capacity);
	}

	/**
	 * Check if the inventory is full.
	 * @return Whether or not the inventory is full.
	 */
	public boolean isFull() {
		return items.size() == capacity;
	}

	/**
	 * Add an item to the inventory, mapping it to the name of the item,
	 * unless a non-null item is already mapped to that key.
	 * If a non-null value is already mapped to this key, then the
	 * preexisting value will not be overwritten and the method will
	 * return this preexisting value. This means that all new items
	 * added to the inventory must have unique names to serve as keys.
	 * PRECONDITION: isFull() returns false.
	 * @param item The item to add to the inventory.
	 * @return Returns null if the item was successfully added to the
	 * inventory, otherwise returns the item currently mapped to the same
	 * name.
	 */
	public Item addItem(Item item) {
		return items.putIfAbsent(item.getName(), item);
	}

	/**
	 * Remove an item from the inventory by name.
	 * @param name The name of the item to remove.
	 * @return The removed item (null if the removed item was equal to null
	 * or if no mapping for the specified key was found in the inventory)
	 */
	public Item removeItem(String name) {
		return items.remove(name);
	}

	/**
	 * Check if an item is in the inventory by name.
	 * @param name The name of the item.
	 * @return True if the item is in the inventory, false otherwise.
	 */
	public boolean containsItem(Item item) {
		return items.containsValue(item);
	}
	
	/**
	 * Check if an item is in the inventory by name.
	 * @param name The name of the item.
	 * @return True if the item is in the inventory, false otherwise.
	 */
	public boolean containsItem(String name) {
		return items.containsKey(name);
	}

	/**
	 * Retrieve a read-only map of the items in the inventory.
	 * @return A read-only map of the items in the inventory.
	 */
	public Map<String, Item> getItems() {
		return Collections.unmodifiableMap(items);
	}
}
