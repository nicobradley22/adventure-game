package game;

/**
 * MultipleCommand.java
 *
 * Class to allow the sequential execution of multiple commands.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists

public class MultipleCommand implements Command {

	/**
	 * The list of Command objects whose execute methods should be
	 * sequentially called.
	 */
	private List<Command> commands;

	/**
	 * Constructor.
	 * @param commands The list of Command objects whose execute methods
	 * should be sequentially called.
	 */
	public MultipleCommand(List<Command> commands) {
		this.commands = commands;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		for (Command c : commands) {
			// execute the commands in order
			c.execute(arg);
		}
	}

}
