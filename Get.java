package game;

/**
 * Get.java
 *
 * This class allows the player to pick up an item from the current room and
 * add it to their inventory.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Arrays; // for lists
import java.util.List; // for lists
import java.util.Map; // for maps

public class Get extends ItemCommand {

	/**
	 * The player's inventory.
	 */
	private Inventory inventory;

	/**
	 * Constructor.
	 * @param inventory The player's inventory.
	 */
	public Get(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Retrieve a list of maps to search for the specified item.
	 * @return The list of maps to search for the specified item.
	 */
	protected List<Map<String, Item>> getMapsToSearch() {
		return Arrays.asList(Game.getInstance().getCurrentRoom().getItems());
	}

	/**
	 * Perform the action associated with failure to find an item with the
	 * name specified by the player.
	 * @param name The item name specified by the player.
	 */
	protected void itemNotFoundAction(String name) {
		System.out.println("You don't see any item with the name \"" + name +
			"\" in this room.");
	}

	/**
	 * Perform the action associated with successful identification of an
	 * item wih the name specified by the player.
	 * @param item The item with the specified name.
	 */
	protected void itemFoundAction(Item item) {
		String name = item.getName();
		if (!item.getCarriable()) {
			System.out.println("You can't pick up this item.");
		} else if (inventory.containsItem(name)) {
			// an item with the same name already exists in the inventory,
			// so the new item cannot be moved to the inventory
			System.out.println("You already have one of those.");
		} else if (inventory.isFull()) {
			System.out.println("Your inventory is full. You can't " +
				"pick anything else up.");
		} else { // add the item to the inventory and remove it from the room
			inventory.addItem(item);
			Game.getInstance().getCurrentRoom().removeItem(name);
			System.out.println("The " + name + " has been added to your " +
				"inventory.");
		}
	}

}
