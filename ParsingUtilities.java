package game;

/**
 * ParsingUtilities.java
 *
 * A class containing various methods for standardizing the formatting and
 * parsing of input from the keyboard.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Scanner; // for keyboard input

public class ParsingUtilities {

	/**
	 * Scanner object to read input from the keyboard.
	 */
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * Format text for parsing.
	 * Makes text lowercase, removes definite and indefinite articles, replaces
	 * one or more consecutive whitespace characters with a single space, 
	 * removes all characters that are neither alphanumeric nor spaces, and
	 * trims leading and trailing spaces.
	 * @param text The text to format.
	 * @return The formatted text.
	 */
	public static String formatText(String text) {
		return text.toLowerCase().replaceAll("^the\\s|an?\\s", "").replaceAll(
			"\\sthe\\s|\\san?\\s+", " ").replaceAll("\\s", " ").replaceAll(
			"[^a-z 0-9]", "").trim();
	}

	/**
	 * Prompt the user for input from the keyboard.
	 * @param prompt The message to display to the user as a prompt for input.
	 * @return The formatted input.
	 */
	private static String promptForArgument(String prompt) {
		System.out.print(prompt + "--> ");
		return formatText(keyboard.nextLine());
	}
	
	/**
	 * Check if the given argument is an empty string and, if so, prompt
	 * the user to input the argument from the keyboard. Otherwise, return
	 * a formatted version of the argument.
	 * @param argument The argument to check.
	 * @param prompt The message to display to the user as a prompt for input.
	 * @return The formatted argument.
	 */
	public static String getArgument(String argument, String prompt) {
		argument = formatText(argument);
		if (argument.equals("")) {
			return promptForArgument(prompt);
		} else {
			return formatText(argument);
		}
	}

}
