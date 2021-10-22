package game;

/**
 * Hints.java
 *
 * This class prints out a list of hints about the game to the player.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class Hints extends PrintListCommand {

	/**
	 * Constructor.
	 */
	public Hints() {
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		System.out.println("Here are some hints about the game: ");
		printList(Game.getInstance().getHints());
	}

}
