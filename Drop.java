package game;

/**
 * Drop.java
 *
 * This class allows the player to remove an item from their inventory and
 * add it to the current room.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Arrays; // for lists
import java.util.List; // for lists
import java.util.Map; // for maps

public class Drop extends ItemCommand {

	/**
	 * The player's inventory.
	 */
	private Inventory inventory;

	/**
	 * Constructor.
	 * @param inventory The player's inventory.
	 */
	public Drop(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Retrieve a list of maps to search for the specified item.
	 * @return The list of maps to search for the specified item.
	 */
	protected List<Map<String, Item>> getMapsToSearch() {
		return Arrays.asList(inventory.getItems());
	}

	/**
	 * Perform the action associated with failure to find an item with the
	 * name specified by the player.
	 * @param name The item name specified by the player.
	 */
	protected void itemNotFoundAction(String name) {
		System.out.println("You don't have an item with the name \"" + name +
			"\" in your inventory.");
	}

	/**
	 * Perform the action associated with successful identification of an
	 * item wih the name specified by the player.
	 * @param item The item with the specified name.
	 */
	protected void itemFoundAction(Item item) {
		Room room = Game.getInstance().getCurrentRoom();
		String name = item.getName();
		if (room.containsItem(name)) {
			// if an item with this name already exists in the current room,
			// the player should not be allowed to drop the item because
			// if they do, it will disappear
			System.out.println("You can't drop this item here.");
		} else {
			// remove the item from the player's inventory and add it to the
			// current room
			inventory.removeItem(name);
			room.addItem(item);
			System.out.println("The " + name + " has been removed from " +
				"your inventory.");
		}
	}

}
