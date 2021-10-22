package game;

/**
 * Use.java
 *
 * This class allows the player to use an item by executing the command
 * associated with that item (if any).
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Arrays; // for lists
import java.util.List; // for lists
import java.util.Map; // for maps

public class Use extends ItemCommand {

	/**
	 * The player's inventory.
	 */
	private Inventory inventory;

	/**
	 * Constructor.
	 * @param inventory The player's inventory.
	 */
	public Use(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Retrieve a list of maps to search for the specified item.
	 * @return The list of maps to search for the specified item.
	 */
	protected List<Map<String, Item>> getMapsToSearch() {
		return Arrays.asList(inventory.getItems(),
			Game.getInstance().getCurrentRoom().getItems());
	}

	/**
	 * Perform the action associated with failure to find an item with the
	 * name specified by the player.
	 * @param name The item name specified by the player.
	 */
	protected void itemNotFoundAction(String name) {
		System.out.println("You don't see an item with the name \"" + name +
			"\" in your inventory or in the room.");
	}

	/**
	 * Perform the action associated with successful identification of an
	 * item wih the name specified by the player.
	 * @param item The item with the specified name.
	 */
	protected void itemFoundAction(Item item) {
		Command command = item.getCommand();
		if (command != null) {
			command.execute("");
		} else {
			System.out.println("Nothing happened.");
		}
	}

}
