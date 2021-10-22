package game;

/**
 * Examine.java
 *
 * This class allows the player to examine an object in the game.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Scanner; // for keyboard input

public class Examine implements Command {

	/**
	 * Inventory object to allow interaction with the inventory.
	 */
	private Inventory inventory;

	/**
	 * Scanner object to read input from the keyboard.
	 */
	private Scanner keyboard;

	/**
	 * Constructor.
	 * @param inventory The player's inventory.
	 */
	public Examine(Inventory inventory) {
		this.inventory = inventory;
        keyboard = new Scanner(System.in);
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		Room room = Game.getInstance().getCurrentRoom(); // the current room
		// get and format the name of what the player wants to examine
		String name = ParsingUtilities.getArgument(arg,
			"What do you want to examine?");
		String description; // the description to display to the player
		if (name.equals("area")) {
			description = "the area in which you are currently located.\n" +
				room.getDescription();
		} else if (room.containsItem(name)) {
			description = room.getItem(name).getDescription() + ".";
		} else if (inventory.containsItem(name)) {
			description = inventory.getItems().get(name).getDescription() + ".";
		} else if (room.containsExit(name) &&
		room.getExits().get(name).getVisible()) { // check if exit is visible
			description = room.getExits().get(name).getDescription() + ".";
		} else if (room.containsMonster() &&
		room.getMonster().getName().equals(name)) {
			description = room.getMonster().getDescription() + ".";
		} else { // nothing examinable was found
			description = "nothing that you can examine with the name \"" +
				name + "\".";
		}
		System.out.println("You see " + description);
	}

}
