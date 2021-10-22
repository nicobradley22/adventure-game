package game;

/**
 * LimitedUseCommand.java
 *
 * Class to define a command that will call the execute method of its
 * subcommand only if the maximum number of times that the subcommand
 * may be executed has not been exceeded.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class LimitedUseCommand extends ConditionalCommand {

	/**
	 * The remaining number of times that the subcommand may be executed.
	 */
	private int executionsLeft;

	/**
	 * Constructor.
	 * @param subcommand The command that should be executed for a limited
	 * number of times.
	 * @param maxExecutions The maximum number of times that the subcommand
	 * may be executed.
	 */
	public LimitedUseCommand(Command subcommand, int maxExecutions) {
		super(subcommand, "Nothing happened.");
		executionsLeft = maxExecutions;
	}

	/**
	 * Check if the subcommand should be executed.
	 * @return Whether or not the subcommand should be executed.
	 */
	protected boolean isExecutable() {
		return (executionsLeft > 0);
	}

	/**
	 * Execute the subcommand.
	 * PRECONDITION: This method should only be called if isExecutable()
	 * returns false.
	 * POSTCONDITION: The subcommand has been executed and the number of
	 * executions left has been decremented.
	 * @param arg Argument from the user's command.
	 */
	@Override
	protected void executeCommand(String arg) {
		super.executeCommand(arg);
		executionsLeft--;
	}
}
