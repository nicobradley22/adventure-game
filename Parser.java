package game;

/**
 * Parser.java
 * 
 * Class to interpret and execute the user's commands.
 * Serves as the View in the Model-View-Controller pattern.
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Scanner; // for keyboard input
import java.util.List; // for lists
import java.util.Arrays; // for lists
import java.util.Map; // for maps

public class Parser {

    /**
     * For user input from the keyboard.
     */
    private Scanner keyboard;

	/**
	 * Inventory object to allow interaction with the inventory.
	 */
	private Inventory inventory;

	/**
	 * Travel object to allow travel between rooms.
	 */
	private Travel travel;

    /**
     * Constructor.
	 * @param inventory The Inventory object.
	 * POSTCONDITION: The global commands map has been populated.
     */
    public Parser(Inventory inventory) {
		this.inventory = inventory;
		this.travel = new Travel();
        keyboard = new Scanner(System.in);
    }

	/**
	 * Search the specified list of command maps for the Command object
	 * mapped to the specified key.
	 * @param key The key.
	 * @return The Command object mapped to the specified key, or null if
	 * none was found.
	 */
	private Command findCommand(List<Map<String, Command>> maps, String key) {
		for (Map<String, Command> map : maps) {
			if (map.containsKey(key)) {
				return map.get(key);
			}
		}
		return null;
	}

	/**
	 * Try to execute the player's command.
	 * Search for an Exit or Command object corresponding to the specified
	 * string. If no match is found, repeat the search with the previous
	 * search substring minus the last word. (Words are assumed to be delimited 
	 * by spaces.) Repeat until either a match is found or the string is
	 * exhausted. If an Exit object is found, try to travel through the exit.
	 * If a Command object is found, call its execute() method and pass it
	 * the remainder of the command as an argument.
	 * @param command The command string.
	 * @param commandMaps A list of the command maps to search for the given
	 * command in order.
	 * @return True if a matching Exit or Command object was found, false
	 * otherwise.
	 */
	private boolean executeCommand(String command, List<Map<String, Command>>
	commandMaps) {
		Room room = Game.getInstance().getCurrentRoom(); // the current room
		String toSearch = command; // the substring to search each iteration
		for (int i = toSearch.length(); i > 0; i = toSearch.lastIndexOf(" ")) {
			toSearch = toSearch.substring(0, i);
			if (room.getExits().containsKey(toSearch)) {
				// travel to the specified room
				travel.travel(room.getExits().get(toSearch));
				return true;
			} else {
				Command commandObject = findCommand(commandMaps, toSearch);
				if (commandObject != null) {
					commandObject.execute(command.substring(i).trim());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Let the user make one "turn" at this game.
     * Initiate any active event in the room, prompt for
     * a command, and interpret the command.
     */
    public void executeTurn() {
		Game game = Game.getInstance(); // the Game object
		if (game.isOver()) { // end the turn if the game is over
			return;
		}
		// a list of the command maps to search
		List<Map<String, Command>> commandMaps = Arrays.asList(
			game.getCurrentRoom().getCommands(), game.getGlobalCommands());

		System.out.println("-----------------------------------------");
		System.out.println("This is turn #" + game.getTurns() + ".");
        System.out.print("Enter command--> ");
		
		// get and format command from user
		String input = keyboard.nextLine(); // the player's input
		System.out.println("-----------------------------------------");
		String formattedInput = ParsingUtilities.formatText(input);

		// try to execute the player's command
		if (!executeCommand(formattedInput, commandMaps)) {
			System.out.println("I do not know how to \"" + input + "\".");
		}
	}

}
