package game;

/**
 * Look.java
 *
 * This class prints out a list of the items and visible exits in the current
 * room in the game.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.Map; // for maps

public class Look extends PrintListCommand {

	/**
	 * Constructor.
	 */
	public Look() {
	}

	private void printItems() {
		Collection<String> items =
			Game.getInstance().getCurrentRoom().getItems().keySet();
		if (items.isEmpty()) {
			System.out.println("You don't see any items in this area.");
		} else {
			System.out.println("You see the following item(s):");
			printList(items);
		}
	}

	/**
	 * Print out a list of the visible exits in the room or, if there
	 * are none, a message notifying the player of that fact.
	 */
	private void printVisibleExits() {
		Map<String, Exit> exits =
			Game.getInstance().getCurrentRoom().getExits();
		int foundVisible = 0;
		for (String exit : exits.keySet()) {
			if (exits.get(exit).getVisible()) {
				// the exit is visible to the user
				foundVisible++;
				if (foundVisible == 1) {
					System.out.println("You see the following exit(s):");
				}
				System.out.println("* " +  exit);
			}
		}
		if (foundVisible == 0) {
			System.out.println("You don't see any exits in this area.");
		}
	}
	

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		System.out.println("You look around.");
		// print out the items in the room (if any)
		printItems();
		// print out the visible exits in the room (if any)
		printVisibleExits();
	}

}
