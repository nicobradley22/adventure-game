package game;

/**
 * IncompatibleItem.java
 *
 * Class to define an item that can only be added to the player's inventory
 * if none of the specified items is present in the inventory.
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

public class IncompatibleItem extends GenericItem {

	/**
	 * The collection of items with which this item is incompatible.
	 */
	private Collection<Item> items;

	/**
	 * The Inventory object.
	 */
	private Inventory inventory;

	/**
	 * The message to display to the player if they try to pick up this
	 * item while carrying one or more of the items with which this item
	 * is incompatible.
	 */
	private String refusalMessage;

	/**
	 * Constructor.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param items The collection of items to check.
	 * @param refusalMessage The message to display to the player if they
	 * try to pick up this item while carrying one or more of the items
	 * with which this item is incompatible.
	 * @param inventory The player's inventory.
	 */
	public IncompatibleItem(String name, String description, Collection<Item>
	items, String refusalMessage, Inventory inventory) {
		super(name, description, false);
		this.items = items;
		this.refusalMessage = refusalMessage;
		this.inventory = inventory;
	}

	/**
	 * Add an item with which this item should be incompatible.
	 * @param item The item to add.
	 */
	public void addIncompatibleItem(Item item) {
		items.add(item);
	}

	/**
	 * Can this item be carried?
	 * If the item cannot be carried, the player will be notified why.
	 * @return True if this item is carriable, false if not.
	 */
	@Override
	public boolean getCarriable() {
		// whether or not the item is carriable
		boolean carriable =
			Collections.disjoint(items, inventory.getItems().values());
		if (!carriable) {
			System.out.println(refusalMessage);
		}
		return carriable;
	}

}
