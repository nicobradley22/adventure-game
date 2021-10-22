package game;

/**
 * ManualExit.java
 *
 * Class to define an exit that must be opened and closed manually by calling
 * the appropriate method(s).
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class ManualExit extends GenericExit {

	/**
	 * Whether or not this exit is traversable.
	 */
	private boolean traversable;

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 */
	public ManualExit(Room destination, String description) {
		this(destination, description, null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param openMessage The message to display to the player when this
	 * exit is about to open.
	 * @param closeMessage The message to display to the player when this
	 * exit is about to close.
	 * @param interval The interval at which this exit should be traversable.
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit should be visible to the player.
	 */
	public ManualExit(Room destination, String description, String
	failureMessage, String successMessage, boolean visible) {
		super(destination, description, failureMessage, successMessage,
			visible);
		traversable = false;
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		return traversable;
	}

	/**
	 * Set whether or not this exit is traversable.
	 * @param traversable Whether or not the exit should be traversable.
	 */
	public void setTraversable(boolean traversable) {
		this.traversable = traversable;
	}

}
