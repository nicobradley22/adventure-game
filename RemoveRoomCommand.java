package game;

/**
 * RemoveRoomCommand.java
 *
 * This class removes a command from the specified room in the game when the
 * execute method of this command is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class RemoveRoomCommand implements Command {

	/**
	 * The name of the command that should be removed.
	 */
	private String command;

	/**
	 * The room from which the command should be removed.
	 */
	private Room room;

	/**
	 * Constructor.
	 * @param room The room from which the command should be removed.
	 * @param command The name of the command that should be removed.
	 */
	public RemoveRoomCommand(Room room, String command) {
		this.room = room;
		this.command = command;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		room.removeCommand(command);
	}
}
