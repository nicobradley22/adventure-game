package game;

/**
 * TimeLimitRoom.java
 *
 * Class to define a room where a penalty will occur if the player spends
 * more than the maximum allowed number of consecutive turns in the room.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class TimeLimitRoom extends Room {

	/**
	 * The maximum number of consecutive turns that the player is allowed
	 * to spend in this room without penalty.
	 */
	private int maxTurns;

	/**
	 * The number of consecutive turns that the player has left to spend
	 * in this room without penalty.
	 */
	private int turnsLeft;

	/**
	 * The most recent turn that the player spent in this room (if any).
	 */
	private int mostRecentTurn;

	/**
	 * The message to display to the player each turn when they are in
	 * this room until they exhaust the number of consecutive turns that
	 * they are allowed to spend in this room without penalty.
	 */
	private String warningMessage;

	/**
	 * The command to execute when the player has exhausted the number of
	 * consecutive turns that they are allowed to spend in this room without
	 * penalty.
	 */
	private Command penaltyCommand;

	/**
	 * Constructor.
	 * @param description A String describing this room to the user.
	 * @param warningmessage The message to display to the player each turn
	 * when they are in this room until they exhaust the number of consecutive
	 * turns that they are allowed to spend in this room without penalty.
	 * @param penaltyCommand The command to execute when the player has
	 * exhausted the number of consecutive turns that they are allowed to
	 * spend in this room without penalty.
	 * @param maxTurns The maximum number of consecutive turns that the player
	 * is allowed to spend in this room without penalty.
	 */
	public TimeLimitRoom(String description, String warningMessage, Command
	penaltyCommand, int maxTurns) {
		super(description);
		this.warningMessage = warningMessage;
		this.penaltyCommand = penaltyCommand;
		this.maxTurns = maxTurns;
		mostRecentTurn = -1;
	}


	/**
	 * If this room is the current room in the game, take the action 
	 * associated with it (if any).
	 * PRECONDITION: This room is the current room in the game.
	 */
	public void act() {
		int currentTurn = Game.getInstance().getTurns();
		if (currentTurn != mostRecentTurn+1) {
			// the player was not in this room on the previous turn,
			// so reset turnsLeft to maxTurns
			turnsLeft = maxTurns;
		}
		if (turnsLeft > 0) {
			System.out.println(warningMessage);
			turnsLeft--;
		} else {
			penaltyCommand.execute("");
		}
		mostRecentTurn = currentTurn;
	}
}
