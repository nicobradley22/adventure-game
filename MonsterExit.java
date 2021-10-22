package game;

/**
 * MonsterExit.java
 *
 * Class to define an invisible exit that only monsters can go through.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class MonsterExit extends GenericExit {

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 */
	public MonsterExit(Room destination) {
		// the only information associated with this exit
		// is its destination and its invisibility
		super(destination, null, null, null, false);
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		return false;
	}

}

