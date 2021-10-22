package game;

/**
 * RemoveExit.java
 *
 * This class removes an exit from the specified room in the game when the
 * execute method of this command is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class RemoveExit implements Command {

	/**
	 * The name of the exit that should be removed from the specified room.
	 */
	private String exit;

	/**
	 * The room from which the exit should be removed.
	 */
	private Room room;

	/**
	 * Constructor.
	 * @param room The room from which the exit should be removed.
	 * @param exit The name of the exit that should be removed from the
	 * specified room.
	 */
	public RemoveExit(Room room, String exit) {
		this.room = room;
		this.exit = exit;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		room.removeExit(exit);
	}
}
