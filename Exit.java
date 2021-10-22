package game;

/**
 * Exit.java
 *
 * Abstract class to define an exit from one room to another.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */
public abstract class Exit {

	/**
	 * The room to which this exit leads.
	 */
	private Room destination;

	/**
	 * The description associated with this exit.
	 */
	private String description;

	/**
	 * The message associated with successful travel through this exit.
	 */
	private String successMessage;

	/**
	 * The message associated with unsuccessful travel through this exit.
	 */
	private String failureMessage;
	
	/**
	 * Keeps track of whether or not this exit is visible to the player.
	 */
	private boolean visible;

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 */
	public Exit(Room destination, String description)  {
		this(destination, description, null, null, true);
	}

	/**
	 * Constructor.
	 * @param destination The room to which this exit leads.
	 * @param description The description associated with this exit.
	 * @param failureMessage The message associated with unsuccessful travel
	 * through this exit.
	 * @param successMessage The message associated with successful travel
	 * through this exit.
	 * @param visible Whether or not this exit should be visible to the player.
	 */
	public Exit(Room destination, String description, String failureMessage,
	String successMessage, boolean visible) {
		this.destination = destination;
		if (description != null) {
			this.description = description;
		} else {
			this.description = "the exit";
		}
		if (failureMessage != null) {
			this.failureMessage = failureMessage;
		} else {
			this.failureMessage = "You can't go that way.";
		}
		if (successMessage != null) {
			this.successMessage = successMessage;
		} else {
			this.successMessage = "You travel through the exit.";
		}
		this.visible = visible;
	}

	/**
	 * Retrieves a reference to the room to which this exit leads.
	 * @return The room to which this exit leads.
	 */
	public Room getDestination() { return destination; }

	/**
	 * Set the room to which this exit leads.
	 * @param room The room.
	 */
	public void setDestination(Room destination) {
		this.destination = destination;
	}

	/**
	 * Retrieve the description associated with traveling through this exit.
	 * @return The description associated with traveling through this exit.
	 */
	public String getDescription() { return description; }

	/**
	 * Set the description associated with this exit.
	 * @param description The description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieve the message associated with successful travel through this
	 * exit.
	 * @return The message associated with successful travel through this
	 * exit.
	 */
	public String getSuccessMessage() { return successMessage; }

	/**
	 * Retrieve the message associated with unsuccessful travel through this
	 * exit.
	 * @return The message associated with unsuccessful travel through this
	 * exit.
	 */
	public String getFailureMessage() { return failureMessage; }

	/**
	 * Is this exit visible to the player?
	 * @return True if this exit is visible, false if not.
	 */
	public boolean getVisible() { return visible; }

	/**
	 * Set whether or not this exit is visible to the player.
	 * @param bool Whether or not this exit is visible.
	 */
	public void setVisible(boolean bool) { visible = bool; }

	/**
	 * Is the player allowed to travel through this exit?
	 * @return Whether or not the player is allowed to travel through
	 * this exit.
	 */
	public abstract boolean isTraversable();

	/**
	 * If this exit is in the current room in the game, take the action 
	 * associated with the exit (if any).
	 * PRECONDITION: This exit is in the current room in the game.
	 */
	public abstract void act();

}
