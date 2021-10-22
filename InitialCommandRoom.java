package game;

/**
 * InitialCommandRoom.java
 * 
 * Class to define a room with an associated command that will be executed
 * only on the player's very first turn in that room.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class InitialCommandRoom extends Room {

	/**
	 * The command to execute the first time the player enters the room.
	 */
	private Command command;

	/**
	 * Whether or not the command has been executed yet.
	 */
	private boolean executed;

	/**
	 * Constructor.
	 * @param description A String describing this room to the user.
	 * @param command The command to execute the first time the player
	 * enters the room.
	 */
	public InitialCommandRoom(String description, Command command) {
		super(description);
		this.command = command;
		executed = false;
	}

	/**
	 * If this room is the current room in the game, take the action 
	 * associated with it (if any).
	 * PRECONDITION: This room is the current room in the game.
	 */
	public void act() {
		// execute the command only if it has not already been executed
		if (!executed) {
			command.execute("");
			executed = true;
		}
	}
	
}
