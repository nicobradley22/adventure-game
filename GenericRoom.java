package game;

/**
 * GenericRoom.java
 * 
 * Class to define a room whose act() method does nothing.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class GenericRoom extends Room {

	/**
	 * Constructor.
	 * @param description A String describing this room to the user.
	 */
	public GenericRoom(String description) {
		super(description);
	}

	/**
	 * If this room is the current room in the game, take the action 
	 * associated with it (if any).
	 * PRECONDITION: This room is the current room in the game.
	 */
	public void act() {
		// do nothing
		return;
	}
	
}
