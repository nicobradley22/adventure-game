package game;

/**
 * DependentItem.java
 *
 * Class to define an item that can only be carried if the specified items
 * are present in the player's inventory.
 * Class invariant: the name of the item, once set through the constructor,
 * does not change.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.Collections; // for collections

public class DependentItem extends GenericItem {

	/**
	 * Keeps track of whether or not all of the specified items
	 * need to be in the inventory for this item to be carriable.
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
	 * The message to display to the player if they try to pick up this
	 * item without having the required item(s) in their inventory.
	 */
	private String refusalMessage;

	/**
	 * Constructor.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param items The collection of items to check.
	 * @param refusalMessage The message to display to the player if they
	 * try to pick up this item without having the required item(s) in their
	 * inventory.
	 * @param inventory The player's inventory.
	 * @param universallyQuantified Whether or not all items need to be
	 * in the inventory for this item to be carriable (true if all items
	 * need to be present, false if only one item needs to be present).
	 */
	public DependentItem(String name, String description, Collection<Item>
	items, String refusalMessage, Inventory inventory, boolean
	universallyQuantified) {
		super(name, description, false);
		this.items = items;
		this.refusalMessage = refusalMessage;
		this.inventory = inventory;
		this.universallyQuantified = universallyQuantified;
	}

	/**
	 * Can this item be carried?
	 * If the item cannot be carried, the player will be notified why.
	 * @return True if this item is carriable, false if not.
	 */
	@Override
	public boolean getCarriable() {
		// a collection of the items in the inventory
		Collection<Item> inventoryItems = inventory.getItems().values();
		boolean carriable;
		if (universallyQuantified) {
			// all of the items must be in the inventory for the item to be
			// carriable
			carriable = inventoryItems.containsAll(items);
		} else {
			// only one of the items must be in the inventory for the item to
			// be carriable
			carriable = !Collections.disjoint(items, inventoryItems);
		}
		if (!carriable) {
			System.out.println(refusalMessage);
		}
		return carriable;
	}

}
