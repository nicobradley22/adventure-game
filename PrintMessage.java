package game;

/**
 * PrintMessage.java
 *
 * This class prints the specified message to the user when the execute
 * method is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class PrintMessage implements Command {

	/**
	 * The message to display when the execute method is called.
	 */
	private String message;

	/**
	 * Constructor
	 */
	public PrintMessage(String message) {
		this.message = message;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		System.out.println(message);
	}

}
