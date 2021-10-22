package game;

/**
 * DefeatMonsterExit.java
 *
 * Class to define an exit from one room to another that is traversable
 * only when the specified monsters are defeated.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class DefeatMonsterExit extends GenericExit {

	/**
	 * Keeps track of whether or not all of the specified monsters
	 * need to be defeated for this exit to be traversable.
	 */
	private boolean universallyQuantified;

	/**
	 * The collection of monsters to check.
	 */
	private Collection<Monster> monsters;

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param monsters The collection of monsters to check.
	 * @param universallyQuantified Whether or not all monsters need to be
	 * defeated for this exit to be traversable (true if all monsters
	 * need to be defeated, false if only one monster needs to be defeated).
	 */
	public DefeatMonsterExit(Room destination, String description,
	Collection<Monster> monsters, boolean universallyQuantified) {
		this(destination, description, monsters, universallyQuantified,
			null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param monsters The collection of monsters to check.
	 * @param universallyQuantified Whether or not all monsters need to be
	 * defeated for this exit to be traversable (true if all monsters
	 * need to be defeated, false if only one monster needs to be defeated).
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit is visible to the player.
	 */
	public DefeatMonsterExit(Room destination, String description,
	Collection<Monster> monsters, boolean universallyQuantified, String
	failureMessage, String successMessage, boolean visible) {
		super(destination, description, failureMessage, successMessage,
			visible);
		this.monsters = monsters;
		this.universallyQuantified = universallyQuantified;
	}

	/**
	 * Check if all of the specified monsters are defeated.
	 * @return True if all of the specified monsters are defeated, false
	 * otherwise.
	 */
	private boolean checkUniversal() {
		for (Monster monster : monsters) {
			if (!monster.getDefeated()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if at least one of the specified monsters is defeated.
	 * @return True if at least one of the specified monsters is defeated,
	 * false otherwise.
	 */
	private boolean checkExistential() {
		for (Monster monster : monsters) {
			if (monster.getDefeated()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		if (universallyQuantified) {
			// all of the monsters must be defeated for the exit to be
			// traversable
			return checkUniversal();
		} else {
			// only one of the monsters must be defeated for the exit to
			// be traversable
			return checkExistential();
		}
	}

}
