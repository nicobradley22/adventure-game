package game;

/**
 * ConditionalCommand.java
 *
 * Abstract class to define a type of command that, whenever its execute
 * method is called, will check the specified condition and then execute
 * the associated subcommand only if that condition is satisfied.
 * The condition to be checked is determined by the subclasses of this class.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public abstract class ConditionalCommand implements Command {

	/**
	 * The command that should be executed only if the associated condition
	 * is true.
	 */
	private Command subcommand;

	/**
	 * The message that should be displayed to the player if the associated
	 * condition is unsatisfied.
	 */
	private String failureMessage;

	/**
	 * Constructor.
	 * @param subcommand The command that should be executed only if the
	 * associated condition is true.
	 * @param failureMessage The message that should be displayed to the
	 * player if the associated condition is unsatisfied.
	 */
	protected ConditionalCommand(Command subcommand, String failureMessage) {
		this.subcommand = subcommand;
		this.failureMessage = failureMessage;
	}

	/**
	 * Check if the subcommand should be executed.
	 * @return Whether or not the subcommand should be executed.
	 */
	protected abstract boolean isExecutable();

	/**
	 * Execute the subcommand.
	 * PRECONDITION: This method should only be called if isExecutable()
	 * returns false.
	 * POSTCONDITION: The subcommand has been executed.
	 * @param arg Argument from the user's command.
	 */
	protected void executeCommand(String arg) {
		// can be overriden by subclasses to allow more specialized behavior
		subcommand.execute(arg);
	}

	/**
	 * Execute the command.
	 * @param arg Argument from the user's command.
	 */
	public void execute(String arg) {
		if (isExecutable()) {
			executeCommand(arg);
		} else {
			System.out.println(failureMessage);
		}
	}

}
