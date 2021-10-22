package game;

/**
 * Teleport.java
 *
 * This class transports the player directly to the specified room.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class Teleport implements Command {

	/**
	 * Travel object to initiate travel to the specified room.
	 */
	private Travel travel;
	
	/**
	 * The room to which to travel.
	 */
	private Room room;

	/**
	 * Constructor.
	 * @param travel The Travel object.
	 * @param room The room to which to travel.
	 */
	public Teleport(Travel travel, Room room) {
		this.travel = travel;
		this.room = room;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		travel.travel(room);
	}

}
