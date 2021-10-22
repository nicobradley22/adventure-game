package game;

/**
 * Help.java
 *
 * This class prints out a list of available commands to the player.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class Help extends PrintListCommand {

	/**
	 * Constructor.
	 */
	public Help() {
	}

	/**
	 * List the global commands to the player.
	 */
	private void printGlobalCommands() {
		System.out.println(
			"Here is a list of commands that are always available:");
		printList(Game.getInstance().getGlobalCommands().keySet());
	}

	/**
	 * List any commands specific to the given room to the player.
	 * @param room The room for which to list the room-specific commands.
	 */
	private void printRoomSpecificCommands(Room room) {
		Collection<String> commands = room.getCommands().keySet();
		if (!commands.isEmpty()) {
			System.out.println("Here is a list of commands specific to " +
				"this area: ");
			printList(commands);
		}
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		printGlobalCommands();
		printRoomSpecificCommands(Game.getInstance().getCurrentRoom());
	}

}
