package game;

/**
 * ItemCommand.java
 *
 * This class provides methods to search for an item in response to input
 * from the player and then execute different code depending on whether or
 * not a match was found.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Map; // for maps
import java.util.List; // for lists

public abstract class ItemCommand implements Command {

	/**
	 * The item name specified by the player.
	 */
	private String name;

	/**
	 * Retrieve a list of maps to search for the specified item.
	 * @return The list of maps to search for the specified item.
	 */
	protected abstract List<Map<String, Item>> getMapsToSearch();

	/**
	 * Search for and retrieve a reference to an item with the specified
	 * name in the specified list of maps.
	 * PRECONDITION: The list of maps is in order of highest to lowest
	 * priority (because, if an item name is found in more than one of the
	 * specified maps, the reference to the earliest match will be returned).
	 * @param maps The list of maps to search for the specified item.
	 * @return A reference to the item (or null if no item with that name
	 * was found).
	 */
	private Item findItem(List<Map<String, Item>> maps) {
		// get and format the item name from the player
		name = ParsingUtilities.getArgument(name, "Which item?");
		// search the specified maps for the item
		for (Map<String, Item> map : maps) {
			if (map.containsKey(name)) {
				return map.get(name); // if the item is found, return it
			}
		}
		// otherwise, return null
		return null;
	}
		
	/**
	 * Perform the action associated with failure to find an item with the
	 * name specified by the player.
	 * @param name The item name specified by the player.
	 */
	protected abstract void itemNotFoundAction(String name);

	/**
	 * Perform the action associated with successful identification of an
	 * item wih the name specified by the player.
	 * @param item The item with the specified name.
	 */
	protected abstract void itemFoundAction(Item item);

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 * @return True if the command was successfully executed
	 * (i.e. if the game world was modified), false otherwise.
	 */
	public void execute(String arg) {
		// template method
		name = arg;
		// search for the item in the list of maps specified at runtime
		Item item = findItem(getMapsToSearch());
		if (item == null) {
			itemNotFoundAction(name);
		} else {
			itemFoundAction(item);
		}
	}

}
