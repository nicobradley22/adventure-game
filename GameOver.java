package game;

/**
 * GameOver.java
 *
 * This class ends the game with an optional accompanying message.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class GameOver implements Command {

	/**
	 * The message to display when the Game Over is initiated.
	 */
	private String message;

	/**
	 * Constructor.
	 * @param message The message to display when the Game Over is initialized
	 * (none if null).
	 */
	public GameOver(String message) {
		this.message = message;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		if (message != null) {
			System.out.println(message);
		}
		Game.getInstance().finishGame();
	}

}
