package game;

/**
 * Travel.java
 *
 * Handles travel from one room in the game to another.
 * Provides methods for travel given a Room object, an Exit object, or the
 * name of an exit.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class Travel implements Command {

	/**
	 * Constructor.
	 */
	public Travel() {
	}

	/**
	 * Travel to the specified room.
	 * @param room The room to which to travel.
	 * POSTCONDITION: The current room of the game has been set to the
	 * specified room. If the previous room contained a non-null event,
	 * that event has been reset, and if it should be active next time
	 * the player enters that room, it has been activated.
	 */
	public void travel(Room room) {
		// travel through the exit
		Game.getInstance().setCurrentRoom(room);
		// display the description of the new room
		System.out.println(room.getDescription());
	}

	/**
	 * Travel to another room via the specified exit.
	 * @param exit The exit through which to travel.
	 * POSTCONDITION: If the exit is not both visible and traversable,
	 * no change has been made. Otherwise, the current room of the game has
	 * been set to the specified room. If the previous room contained a
	 * non-null event, that event has been reset, and if it should be active
	 * next time the player enters that room, it has been activated.
	 */
	public void travel(Exit exit) {
		// allow player to travel through this exit only if the exit is both
		// visible and traversable
		if (!exit.getVisible()) {
			System.out.println("You don't see any exit with that name.");
		} else if (exit.isTraversable()) {
			System.out.println(exit.getSuccessMessage());
			travel(exit.getDestination());
		} else {
			System.out.println(exit.getFailureMessage());
		}
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		// travel through an exit identified by name
		// get the name of the exit
		String name = ParsingUtilities.getArgument(arg, "In which direction?");
		// try to get a reference to the specified exit from the current room
		Exit exit = Game.getInstance().getCurrentRoom().getExits().get(name);
		if (exit != null) {
			travel(exit);
		} else {
			System.out.println("There is no exit in that direction.");
		}
	}
}
