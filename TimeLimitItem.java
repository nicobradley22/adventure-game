package game;

/**
 * TimeLimitItem.java
 *
 * Class to define an item that disappears from the player's inventory
 * after a set number of turns.
 * Class invariant: the name of the item, once set through the constructor,
 * does not change
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class TimeLimitItem extends Item {

	/**
	 * The number of turns after which this item will disappear from the
	 * player's inventory.
	 */
	private int turnsLeft;

	/**
	 * The message to display to the player each turn that this item is in
	 * their inventory until it disappears.
	 */
	private String warningMessage;

	/**
	 * The message to display to the player when this item disappears from
	 * their inventory.
	 */
	private String disappearMessage;

	/**
	 * Constructor.
	 * Since this item's act method has the precondition that it should
	 * only take effect if it is in the player's inventory, carriable will
	 * be initialized to true.
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param warningMessage The message to display to the player each turn
	 * that this item is in their inventory until it disappears.
	 * @param disappearMessage The message to display to the player when this
	 * item disappears from their inventory.
	 * @param turns The number of turns after which this item will disappear
	 * from the player's inventory.
	 */
	public TimeLimitItem(String name, String description, String
	warningMessage, String disappearMessage, int turns) {
		super(name, description, true);
		this.warningMessage = warningMessage;
		this.disappearMessage = disappearMessage;
		this.turnsLeft = turns;
	}


	/**
	 * If the item is in the player's inventory, initiate the action
	 * associated with this item (if any).
	 * PRECONDITION: The item is in the player's inventory.
	 */
	public void act() {
		if (turnsLeft > 0) {
			System.out.println(warningMessage);
			turnsLeft--;
		} else {
			System.out.println(disappearMessage);
			Game.getInstance().getInventory().removeItem(getName());
		}
	}
}
