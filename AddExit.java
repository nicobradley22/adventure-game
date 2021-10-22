package game;

/**
 * AddExit.java
 *
 * This class adds an exit to the specified room in the game when the
 * execute method of this command is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class AddExit implements Command {

	/**
	 * The name that should be mapped to the exit.
	 */
	private String name;

	/**
	 * The exit that should be added to the specified room.
	 */
	private Exit exit;

	/**
	 * The room to which the exit should be added.
	 */
	private Room room;

	/**
	 * Constructor.
	 * @param room The room to which the exit should be added.
	 * @param name The name that should be mapped to the exit.
	 * @param exit The exit that should be added to the specified room.
	 */
	public AddExit(Room room, String name, Exit exit) {
		this.room = room;
		this.name = name;
		this.exit = exit;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		room.addExit(name, exit);
	}
}
