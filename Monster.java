package game;

/**
 * Monster.java
 * 
 * A class to define monsters.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections
import java.util.List; // for lists
import java.util.ArrayList; // for lists
import java.util.Random; // for random number generation

public abstract class Monster {
	
	/**
	 * A collection of items that can defeat the monster.
	 */
	private Collection<Item> kryptonite; 

	/**
	 * A collection of exits through which this monster can travel.
	 */
	private Collection<Exit> exits;

	/**
	 * A description of the monster.
	 */
	private String description;

	/**
	 * The Item that the monster is guarding.
	 */
	private Item prize;

	/**
	 * Whether or not the monster has been defeated.
	 */
	private boolean defeated;
	
	/**
	 * The name of the monster.
	 */
	private String name;
	
	/**
	 * The message displayed to the player if they fail to defeat the monster
	 * in time.
	 */
	private String failureMessage;

	/**
	 * The message displayed to the player when the monster is defeated.
	 */
	private String successMessage;

	/**
	 * The room in which the monster is currently located.
	 */
	private Room room;

	/**
	 * The maximum number of turns that the player can take to defeat the
	 * monster.
	 */
	private int maxTurns;

	/**
	 * The number of turns that the player has taken so far to defeat the
	 * monster.
	 */
	private int turnsSoFar;

	/**
	 * Random object for random number generation.
	 */
	private Random randomObject;

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
	 */
	public Monster(String name, String description, Item prize,
	Collection<Item> kryptonite, Room room, Collection<Exit> exits,
	int maxTurns) {
		this(name, description, prize, kryptonite, room, exits,
			"The monster lunges for you, and your vision goes black.",
			"You defeated the " + name + ".", maxTurns);
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
	 */
	public Monster(String name, String description, Item prize,
	Collection<Item> kryptonite, Room room, Collection<Exit> exits,
	String failureMessage, String successMessage, int maxTurns) {
		this.name = ParsingUtilities.formatText(name);
		this.description = description;
		this.prize = prize;
		this.kryptonite = kryptonite;
		this.room = room;
		this.exits = exits;
		this.failureMessage = failureMessage;
		this.successMessage = successMessage;
		this.maxTurns = maxTurns;
		turnsSoFar = 0;
		randomObject = new Random();
		room.addMonster(this); // add the monster to the room
	}

	/**
	 * Initiate the appropriate action that the monster should take.
	 */
	public void act() {
		if (room == Game.getInstance().getCurrentRoom()) {
			attack();
		} else {
			turnsSoFar = 0; // reset number of turns
			move();
		}
	}

	/**
	 * Try to move to another room.
	 */
	private void move() {
		// a collection of all exits in the room that the monster is in
		Collection<Exit> currentExits = room.getExits().values();
		// a list of all the exits that the monster can go through
		List<Exit> availableExits = new ArrayList<Exit>(currentExits.size());
		for (Exit exit : currentExits) {
			if (exits.contains(exit) &&
			!exit.getDestination().containsMonster()) {
				availableExits.add(exit);
			}
		}
		// choose a random exit for the monster to go through
		if (!availableExits.isEmpty()) {
			// the index of the randomly chosen exit
			Exit randomExit = availableExits.get(
				randomObject.nextInt(availableExits.size()));
			changeRoom(randomExit.getDestination());
		}
	}

	/**
	 * Change the room in which the monster is located.
	 * PRECONDITION: The new room should not already contain a monster.
	 * POSTCONDITION: The monster has moved from the old room to the new one.
	 * @param newRoom The new room to which the monster should move.
	 */
	private void changeRoom(Room newRoom) {
		room.removeMonster(); // remove the monster from the old room
		newRoom.addMonster(this); // add the monster to the new room
		room = newRoom; // update the room instance variable
	}

	/**
	 * Attack the player.
	 */
	private void attack() {
		if (turnsSoFar == 0) {
			System.out.println("Suddenly, a " + name + " appears!");
			turnsSoFar++;
		} else if (turnsSoFar == maxTurns) {
			System.out.println(failureMessage);
			failure();
		} else {
			System.out.println("The " + name + " moves closer.");
			turnsSoFar++;
		}
	}

	/**
	 * Defeat the monster.
	 * POSTCONDITION: The monster has been removed from the game. If the
	 * monster was carrying an item, that item has been added to the current
	 * room.
	 */
	public void defeat() {
		defeated = true;
		System.out.println(successMessage);
		if (prize != null) {
			room.addItem(prize);
			System.out.println("The monster dropped something.");
		}
		// remove the monster from the game
		room.removeMonster();
		Game.getInstance().removeMonster(this);
	}

	/**
	 * The action to take if the player fails to defeat the monster in time.
	 */
	protected abstract void failure();
	
	/**
	 * Returns the name of the monster.
	 * @return A String of the name of the monster.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieve the description of the monster.
	 * @return A String containing the monster's success message.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the Item that the monster is guarding.
	 * @return The Item that the monster is guarding.
	 */
	public Item getPrize() {
		return prize;
	}

	/**
	 * Add an item by which the monster can be defeated.
	 * @param kryptoniteItem The new item to add.
	 */
	public void addKryptonite(Item kryptoniteItem) {
		kryptonite.add(kryptoniteItem);
	}
	
	/**
	 * Determines whether the given user's item is the monster's kryptonite.
	 * @param item The item to compare to the monster's kryptonite.
	 * @return A boolean telling whether or not the user's item is the
	 * monster's kryptonite.
	 */
	public boolean isKryptonite(Item item) {
		return kryptonite.contains(item);
	}
		
	/**
	 * Returns whether or not the monster has already been defeated.
	 * @return A boolean telling if the monster has been defeated.
	 */
	public boolean getDefeated() {
		return defeated;
	}
	
	/**
	 * Sets whether or not the monster has already been defeated.
	 * @param bool The value to assign to the isDefeated variable.
	 */
	public void setDefeated(boolean bool) {
		this.defeated = bool;
	}
	
}
