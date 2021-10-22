package game;

/**
 * CustomizeCommand.java
 *
 * This class allows the addition of a global command mapped to a key
 * specified by the player.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Scanner; // for keyboard input

public class CustomizeCommand implements Command {

    /**
     * For user input from the keyboard.
     */
    private Scanner keyboard;

	/**
	 * The prompt to display to the player when asking them for the key
	 * to which to map the command.
	 */
	private String prompt;

	/**
	 * The command to add to the global commands map.
	 */
	private Command command;

	/**
	 * Constructor.
	 * @param prompt The prompt to display to the player when asking them
	 * for the key to which to map the command.
	 * @param command The command to add to the global commands map.
	 */
	public CustomizeCommand(String prompt, Command command) {
		this.prompt = prompt;
		this.command = command;
		keyboard = new Scanner(System.in);
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		Game game = Game.getInstance();
		System.out.println(prompt);
		boolean validInput = false; // whether or not the input is valid
		while (!validInput) {
			System.out.println("-----------------------------------------");
			System.out.print("Enter the keyword for the command--> ");
			String key = ParsingUtilities.formatText(keyboard.nextLine());
			System.out.println("-----------------------------------------");
			if (game.getGlobalCommands().containsKey(key)) {
				System.out.println("That command is already in use. " +
					"Choose another one.");
			} else if (key.equals("")) {
				System.out.println("That is not a valid command.");
			} else {
				validInput = true;
				game.addGlobalCommand(key, command);
			}
		}
		System.out.println("Your command has been added.");
	}

}
