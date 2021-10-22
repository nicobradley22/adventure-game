package game;

/**
 * MonsterProximityItem.java
 *
 * Class to define an item that reacts to the proximity of monsters.
 * Class invariant: the name of the item, once set through the constructor,
 * does not change
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class MonsterProximityItem extends Item {

	/**
	 * A collection of monsters to whose proximity this item reacts.
	 */
	private Collection<Monster> monsters;

	/**
	 * The message to display to the player when this item reacts to monster
	 * proximity.
	 */
	private String reactMessage;

	/**
	 * Constructor.
	 * Since this item's act method has the precondition that it should
	 * only take effect if it is in the player's inventory, carriable will
	 * be initialized to true.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param monsters A collection of monsters to whose proximity this
	 * item reacts.
	 * @param reactMessage The message to display to the player when this
	 * item reacts to monster proximity.
	 */
	public MonsterProximityItem(String name, String description,
	Collection<Monster> monsters, String reactMessage) {
		super(name, description, true);
		this.monsters = monsters;
		this.reactMessage = reactMessage;
	}

	/**
	 * Add a monster to the collection of monsters to which this item reacts.
	 * @param monster The monster to add.
	 */
	public void addMonster(Monster monster) {
		monsters.add(monster);
	}

	/**
	 * If the item is in the player's inventory, initiate the action
	 * associated with this item (if any).
	 * PRECONDITION: The item is in the player's inventory and the collection
	 * of monsters to which this item should react is not null.
	 */
	public void act() {
		Room room = Game.getInstance().getCurrentRoom();
		// check if there is a monster in the current room to which this
		// item reacts
		if (monsters.contains(room.getMonster())) {
			System.out.println(reactMessage);
		} else { // otherwise, check the adjacent rooms
			Collection<Exit> exits = 
				Game.getInstance().getCurrentRoom().getExits().values();
			for (Exit exit : exits) {
				if (monsters.contains(exit.getDestination().getMonster())) {
					// at least one monster to which this item reacts is 
					// present in an adjacent room
					System.out.println(reactMessage);
					return;
				}
			}
		}
	}
}
