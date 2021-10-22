package game;

/**
 * GenericItem.java
 *
 * Class to define an item whose act() method does nothing.
 * Class invariant: the name of the item, once set through the constructor,
 * does not change.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class GenericItem extends Item {

	/**
	 * Constructor.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param carriable Whether or not the item can be added to the player's
	 * inventory.
	 */
	public GenericItem(String name, String description, boolean carriable) {
		super(name, description, carriable);
	}

	/**
	 * If the item is in the player's inventory, initiate the action
	 * associated with this item (if any).
	 * PRECONDITION: The item is in the player's inventory.
	 */
	public void act() {
		// do nothing
		return;
	}

}
