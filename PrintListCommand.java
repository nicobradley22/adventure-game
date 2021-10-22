package game;

/**
 * PrintListCommand.java
 *
 * Abstract class to define commands that print out each element of the
 * specified collections as a formatted list.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public abstract class PrintListCommand implements Command {

	/**
	 * Print each element of a collection of strings as a list.
	 */
	protected void printList(Collection<String> list) {
		for (String element: list) {
			System.out.println("* " + element);
		}
	}

}
