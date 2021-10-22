package game;

/**
 * Item.java
 *
 * Abstract class to define an item.
 * Class invariant: the name of the item, once set through the constructor,
 * does not change.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public abstract class Item {

	/**
	 * The name of the item.
	 */
	private String name;
	
	/**
	 * The description of the item.
	 */
	private String description;
	
	/**
	 * The command associated with this item.
	 */
	private Command command;

	/**
	 * Whether or not this item can be added to the player's inventory.
	 */
	private boolean carriable;
	
	/**
	 * Constructor.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param carriable Whether or not the item can be added to the player's
	 * inventory.
	 */
	public Item(String name, String description, boolean carriable) {
		this.name = ParsingUtilities.formatText(name);
		this.description = description;
		this.carriable = carriable;
		command = null;
	}

	/**
	 * Retrieve the name of the item.
	 * @return The name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieve the description of the item.
	 * @return The description of the item.
	 */

	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of the item.
	 * @param description The description of the item.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Retrieve the command associated with this item.
	 * @return The command associated with this item (null if there is none).
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Set the command associated with the item.
	 * @param command The command to associate with this item.
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * Can this item be carried?
	 * @return True if this item is carriable, false if not.
	 */
	public boolean getCarriable() {
		return carriable;
	}

	/**
	 * Set whether or not this item can be carried.
	 * @param carriable Whether or not this item is carriable.
	 */
	public void setCarriable(boolean carriable) {
		this.carriable = carriable;
	}

	/**
	 * If the item is in the player's inventory, initiate the action
	 * associated with this item (if any).
	 * PRECONDITION: The item is in the player's inventory.
	 */
	public abstract void act();

}
