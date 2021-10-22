package game;

/**
 * InventoryCommand.java
 *
 * Class to define a command that will call the execute method of its
 * subcommand only if the specified items are present in the player's
 * inventory.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.Collections; // for collections

public class InventoryCommand extends ConditionalCommand {

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
	 * @param subcommand The command to execute if the specified item or
	 * items are present in the player's inventory.
	 * @param items The collection of items to check.
	 * @param inventory The player's inventory.
	 * @param universallyQuantified Whether or not all items need to be
	 * in the inventory for the command to be executed (true if all items
	 * need to be present, false if only one item needs to be present).
	 * @param failureMessage The message that should be displayed to the
	 * player if they do not have all of the required items in their inventory.
	 */
	public InventoryCommand(Command subcommand, Collection<Item> items,
	Inventory inventory, boolean universallyQuantified, String failureMessage) {
		super(subcommand, failureMessage);
		this.items = items;
		this.inventory = inventory;
		this.universallyQuantified = universallyQuantified;
	}

	/**
	 * Check if the subcommand should be executed.
	 * @return Whether or not the subcommand should be executed.
	 */
	protected boolean isExecutable() {
		// a collection of the items in the inventory
		Collection<Item> inventoryItems = inventory.getItems().values();
		if (universallyQuantified) {
			// all of the items must be in the inventory for the command
			// to be executable
			return inventoryItems.containsAll(items);
		} else {
			// only one of the items must be in the inventory for the command
			// to be executable
			return !Collections.disjoint(items, inventoryItems);
		}
	}

}
