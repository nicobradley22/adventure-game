package game;

/**
 * AddItem.java
 *
 * This class adds an item to the specified room in the game when the
 * execute method of this command is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class AddItem implements Command {

	/**
	 * The item that should be added to the specified room.
	 */
	private Item item;

	/**
	 * The room to which the item should be added.
	 */
	private Room room;

	/**
	 * Constructor.
	 * @param room The room to which the item should be added.
	 * @param item The item that should be added to the specified room.
	 */
	public AddItem(Room room, Item item) {
		this.room = room;
		this.item = item;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		room.addItem(item);
	}
}
