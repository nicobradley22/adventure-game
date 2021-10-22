package game;

/**
 * GameArea.java
 *
 * Abstract class to define an area of the game.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.HashSet; // for sets

public abstract class GameArea {

	/**
	 * A collection of all the monsters in this area.
	 */
	private Collection<Monster> monsters;

	/**
	 * Constructor.
	 */
	public GameArea() {
		monsters = new HashSet<Monster>(5);
	}

	/**
	 * Retrieve a collection of all of the monsters in this area.
	 * @return A collection of all of the monsters in this area.
	 */
	public Collection<Monster> getMonsters() {
		return monsters;
	}

	/**
	 * Check if the specified monster is in this area.
	 * @param monster The monster to check.
	 * @return Whether or not the specified monster is in this area.
	 */
	public boolean containsMonster(Monster monster) {
		return monsters.contains(monster);
	}

	/**
	 * Add a monster to this area.
	 * @param monster The monster to add to this area.
	 */
	protected void addMonster(Monster monster) {
		monsters.add(monster);
	}

	/**
	 * Remove a monster from this area.
	 * @param monster The monster to remove.
	 */
	public void removeMonster(Monster monster) {
		monsters.remove(monster);
	}

}
