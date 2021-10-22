package game;

/**
 * GenericExit.java
 *
 * Abstract class to define an exit whose act() method does nothing.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */
public abstract class GenericExit extends Exit {

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 */
	public GenericExit(Room destination, String description)  {
		this(destination, description, null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit should be visible to the player.
	 */
	public GenericExit(Room destination, String description, String
	failureMessage, String successMessage, boolean visible) {
		super(destination, description, failureMessage, successMessage,
			visible);
	}

	/**
	 * If this exit is in the current room in the game, take the action 
	 * associated with the exit (if any).
	 * PRECONDITION: This exit is in the current room in the game.
	 */
	public void act() {
		// do nothing
		return;
	}

}
