package game;

/**
 * ManualExitCommand.java
 *
 * This class manually opens or closes the specified ManualExit exit 
 * when its execute method is called.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class ManualExitCommand implements Command {

	/**
	 * Whether or not the exit should be set to be traversable.
	 */
	private boolean traversable;

	/**
	 * The ManualExit exit that should be set to traversable.
	 */
	private ManualExit exit;

	/**
	 * Constructor.
	 * @param exit The ManualExit exit that should be set to traversable.
	 * @param traversable Whether or not the exit should be set to be
	 * traversable.
	 */
	public ManualExitCommand(ManualExit exit, boolean traversable) {
		this.exit = exit;
		this.traversable = traversable;
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		exit.setTraversable(traversable);
	}
}
