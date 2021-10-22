package game;

/**
 * IntervalExit.java
 *
 * Class to define an exit from one room to another that is traversable for
 * a single turn at regular intervals and intraversable at all other times.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class IntervalExit extends Exit {

	/**
	 * The message to display to the player when this exit is about to open.
	 */
	private String openMessage;

	/**
	 * The message to display to the player when this exit is about to close.
	 */
	private String closeMessage;

	/**
	 * The interval at which this exit should be traversable.
	 */
	private int interval;

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param openMessage The message to display to the player when this
	 * exit is about to open.
	 * @param closeMessage The message to display to the player when this
	 * exit is about to close.
	 * @param interval The interval at which this exit should be traversable.
	 */
	public IntervalExit(Room destination, String description, String
	openMessage, String closeMessage, int interval) {
		this(destination, description, openMessage, closeMessage, interval,
			null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param openMessage The message to display to the player when this
	 * exit is about to open.
	 * @param closeMessage The message to display to the player when this
	 * exit is about to close.
	 * @param interval The interval at which this exit should be traversable.
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit should be visible to the player.
	 */
	public IntervalExit(Room destination, String description, String
	openMessage, String closeMessage, int interval, String failureMessage,
	String successMessage, boolean visible) {
		super(destination, description, failureMessage, successMessage,
			visible);
		this.openMessage = openMessage;
		this.closeMessage = closeMessage;
		this.interval = interval;
	}

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public boolean isTraversable() {
		return Game.getInstance().getTurns() % interval == 0;
	}

	/**
	 * If this exit is in the current room in the game, take the action 
	 * associated with the exit (if any).
	 * PRECONDITION: This exit is in the current room in the game.
	 */
	public void act() {
		int currentTurn = Game.getInstance().getTurns();
		if ((currentTurn-1) % interval == 0) {
			// the exit will be closed next turn
			System.out.println(closeMessage);
		} else if ((currentTurn) % interval == 0) {
			// the exit will be open next turn
			System.out.println(openMessage);
		}
	}
	
}
