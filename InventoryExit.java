package game;

/**
 * InventoryExit.java
 *
 * Class to define an exit from one room to another that is traversable
 * only when the specified items are in the player's inventory.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.Collections; // for collections

public class InventoryExit extends GenericExit {

	/**
	 * Keeps track of whether or not all of the specified items
	 * need to be in the inventory for this exit to be traversable.
	 */
	private boolean universallyQuantified;

	/**
	 * The collection of items to check.
	 */
	private Collection<Item> items;

	/**
	 * The Inventory object.
	 */
	private Inventory inventory;

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param items The collection of items to check.
	 * @param inventory The player's inventory.
	 * @param universallyQuantified Whether or not all items need to be
	 * in the inventory for this exit to be traversable (true if all items
	 * need to be present, false if only one item needs to be present).
	 */
	public InventoryExit(Room destination, String description, Collection<Item>
	items, Inventory inventory, boolean universallyQuantified) {
		this(destination, description, items, inventory, universallyQuantified,
			null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param items The collection of items to check.
	 * @param inventory The player's inventory.
	 * @param universallyQuantified Whether or not all items need to be
	 * in the inventory for this exit to be traversable (true if all items
	 * need to be present, false if only one item needs to be present).
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit is visible to the player.
	 */
	public InventoryExit(Room destination, String description, Collection<Item>
	items, Inventory inventory, boolean universallyQuantified, String
	failureMessage, String successMessage, boolean visible) {
		super(destination, description, failureMessage, successMessage,
			visible);
		this.items = items;
		this.inventory = inventory;
		this.universallyQuantified = universallyQuantified;
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		// a collection of the items in the inventory
		Collection<Item> inventoryItems = inventory.getItems().values();
		if (universallyQuantified) {
			// all of the items must be in the inventory for the exit to be
			// traversable
			return inventoryItems.containsAll(items);
		} else {
			// only one of the items must be in the inventory for the exit to
			// be traversable
			return !Collections.disjoint(items, inventoryItems);
		}
	}

}
