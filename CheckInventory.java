package game;

/**
 * CheckInventory.java
 *
 * This class prints out a list of the items in the specified inventory.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class CheckInventory extends PrintListCommand {

	/**
	 * Inventory object to access inventory items.
	 */
	private Inventory inventory;

	/**
	 * Constructor.
	 * @param inventory The inventory.
	 */
	public CheckInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Execute the command.
	 * @param args Argument from the user's command.
	 */
	public void execute(String args) {
		Collection<String> items = inventory.getItems().keySet();
		if (items.isEmpty()) {
			System.out.println("Your inventory is empty.");
		} else {
			System.out.println("Here are the items in your inventory:");
			printList(items);
		}
	}

}
