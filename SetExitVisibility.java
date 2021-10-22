package game;

/**
 * SetExitVisibility.java
 *
 * This class replaces the current visiblity for the specified exit(s) with
 * with the new specified visiblity.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.Collection; // for collections

public class SetExitVisibility implements Command {

	/**
	 * The exits whose visiblity should be changed.
	 */
	private Collection<Exit> exits;

	/**
	 * The new value to set as the visibility of the specified exits.
	 */
	private boolean visible;

	/**
	 * Constructor.
	 * @param exits The exits whose visiblity should be changed
	 * @param visible The new value to set as the visibility of the specified
	 * exit(s).
	 */
	public SetExitVisibility(Collection<Exit> exits, boolean visible) {
		this.exits = exits;
		this.visible = visible;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		// iterate through the collection of exits and change the visibility
		for (Exit exit : exits) {
			exit.setVisible(visible);
		}
	}

}
