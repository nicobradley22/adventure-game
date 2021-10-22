package game;

/**
 * OpenExit.java
 *
 * Class to define an exit from one room to another that is always traversable.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class OpenExit extends GenericExit {

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 */
	public OpenExit(Room destination, String description) {
		this(destination, description, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit should be visible to the player.
	 */
	public OpenExit(Room destination, String description, String
	successMessage, boolean visible) {
		super(destination, description, null, successMessage,
			visible);
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		return true;
	}

}
