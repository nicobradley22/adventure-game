package game;

/**
 * Defeat.java
 *
 * This class handles defeating monsters with an item.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class Defeat implements Command {

	/**
	 * The item that is being used to try to defeat the monsters.
	 */
	private Item item;

	/**
	 * Constructor.
	 * @param item The item that is being used to try to defeat the monsters.
	 */
	public Defeat(Item item) {
		this.item = item;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		Monster monster = Game.getInstance().getCurrentRoom().getMonster();
		if (monster != null && monster.isKryptonite(item)) {
			monster.defeat();
		} else { // otherwise, do nothing
			System.out.println("Nothing happened.");
		}
	}

}
