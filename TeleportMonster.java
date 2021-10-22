package game;

/**
 * TeleportMonster.java
 * 
 * A class to define a monster that teleports the player to another room
 * if they fail to defeat it in time.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class TeleportMonster extends Monster {
	
	/**
	 * The teleport command to execute if the player fails to defeat this
	 * monster in time.
	 */
	private Teleport teleport;

	/**
	 * Constructor.
	 * @param name The name of the monster.
	 * @param description The description of the monster.
	 * @param prize The item that the monster is guarding.
	 * @param kryptonite The collection of items that can defeat the monster.
	 * @param room The room in which the monster is currently located.
	 * @param exits A collection of exits through which this monster can
	 * travel.
	 * @param maxTurns The maximum number of turns that the player can take
	 * to defeat the monster.
	 * @param teleport The teleport command to execute if the player
	 * fails to defeat this monster in time.
	 */
	public TeleportMonster(String name, String description, Item prize,
	Collection<Item> kryptonite, Room room, Collection<Exit> exits,
	Teleport teleport, int maxTurns) {
		super(name, description, prize, kryptonite, room, exits, maxTurns);
		this.teleport = teleport;
	}

	/**
	 * Constructor.
	 * @param name The name of the monster.
	 * @param description The description of the monster.
	 * @param prize The item that the monster is guarding.
	 * @param kryptonite The collection of items that can defeat the monster.
	 * @param room The room in which the monster is currently located.
	 * @param exits A collection of exits through which this monster can
	 * travel.
	 * @param failureMessage The message displayed to the player if they
	 * fail to defeat the monster in time.
	 * @param successMessage The message displayed to the player when the
	 * monster is defeated.
	 * @param maxTurns The maximum number of turns that the player can take
	 * to defeat the monster.
	 * @param teleport The teleport command to execute if the player
	 * fails to defeat this monster in time.
	 */
	public TeleportMonster(String name, String description, Item prize,
	Collection<Item> kryptonite, Room room, Collection<Exit> exits,
	String failureMessage, String successMessage, int maxTurns,
	Teleport teleport) {
		super(name, description, prize, kryptonite, room, exits,
			failureMessage, successMessage, maxTurns);
		this.teleport = teleport;
	}

	/**
	 * The action to take if the player fails to defeat the monster in time.
	 */
	protected void failure() {
		teleport.execute("");
		System.out.println("You slowly regain consciousness.");
	}
	
}
