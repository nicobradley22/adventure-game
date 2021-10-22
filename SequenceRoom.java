package game;

/**
 * TimeLimitItem.java
 *
 * Class to define a room in a sequence of rooms where, if the player enters
 * each room in the correct order, the specified command will be executed
 * once the player enters the last room of the sequence.
 * The player will be notified when they enter the next room in the sequence
 * if they have correctly followed the sequence up to that point. The correct
 * order of the sequence is determined by the player consecutively entering
 * each room of the sequence in the specified order without going to any
 * other room in between.
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

public class SequenceRoom extends Room {

	/**
	 * The previous room in the sequence.
	 */
	private SequenceRoom prevRoom;

	/**
	 * Whether or not the sequence has been followed so far.
	 */
	private boolean seqFollowed;

	/**
	 * The most recent turn that the player has spent in this room while
	 * following the sequence correctly.
	 */
	private int prevCorrectTurn;

	/**
	 * The message to display to the player to notify them that they have
	 * entered the next room in the sequence if they have correctly followed 
	 * the sequence so far.
	 */
	private String notifyMessage;

	/**
	 * The command to execute once the player enters the final room in the
	 * sequence after following the rest of the sequence correctly.
	 */
	private Command successCommand;
	
	/**
	 * Constructor to use if this is the first room in the sequence.
	 * @param description A String describing this room to the user.
	 * @param notifyMessage The message to display to the player to
	 * notify them that they have entered the next room in the sequence
	 * if they have correctly followed the sequence so far.
	 */
	public SequenceRoom(String description, String notifyMessage) {
		this(description, notifyMessage, null, null);
	}

	/**
	 * Constructor to use if this is neither the first nor the last room
	 * in the sequence.
	 * @param description A String describing this room to the user.
	 * @param notifyMessage The message to display to the player to
	 * notify them that they have entered the next room in the sequence
	 * if they have correctly followed the sequence so far.
	 * @param prevRoom The previous room in the sequence.
	 */
	public SequenceRoom(String description, String notifyMessage,
	SequenceRoom prevRoom) {
		this(description, notifyMessage, prevRoom, null);
	}

	/**
	 * Constructor to use if this is the last room in the sequence.
	 * @param description A String describing this room to the user.
	 * @param notifyMessage The message to display to the player to
	 * notify them that they have entered the next room in the sequence
	 * if they have correctly followed the sequence so far.
	 * @param prevRoom The previous room in the sequence.
	 * @param successCommand The command to execute once the player enters
	 * the final room in the sequence after following the rest of the
	 * sequence correctly.
	 */
	public SequenceRoom(String description, String notifyMessage,
	SequenceRoom prevRoom, Command successCommand) {
		super(description);
		this.notifyMessage = notifyMessage;
		this.prevRoom = prevRoom;
		this.successCommand = successCommand;
		seqFollowed = false;
		prevCorrectTurn = -1;
	}

	/**
	 * Retrieve the number of the most recent turn that the player spent in
	 * this room.
	 */
	protected int getPrevCorrectTurn() {
		return prevCorrectTurn;
	}

	/**
	 * Did the player enter this room in the correct order?
	 * @return Whether or not the player entered this room in the correct
	 * order.
	 */
	protected boolean getSeqFollowed() {
		return seqFollowed;
	}

	/**
	 * Set the command to execute once the player successfully completes
	 * the sequence.
	 * PRECONDITION: This method should only be called if the receiver
	 * is the last room in the sequence.
	 * @param successCommand The command to execute once the player enters
	 * the final room in the sequence after following the rest of the
	 * sequence correctly.
	 */
	public void setSuccessCommand(Command successCommand) {
		this.successCommand = successCommand;
	}

	/**
	 * Reset the sequence of rooms to indicate that the player has not
	 * entered each room in the sequence of rooms in the correct order.
	 * POSTCONDITION: For each room in the sequence of rooms,
	 * getSeqFollowed will return false and getPrevCorrectTurn will
	 * return -1.
	 */
	protected void resetSequence() {
		seqFollowed = false;
		prevCorrectTurn = -1;
		if (prevRoom != null) {
			prevRoom.resetSequence();
		}
	}

	/**
	 * Notify the player that they are following the sequence correctly.
	 * The player will only be notified if they were not already in this
	 * room on the previous turn.
	 * PRECONDITION: This method should only be called if the player has
	 * correctly followed the sequence so far and if prevCorrectTurn has
	 * not yet been updated to be equal to the current turn.
	 */
	private void notifyPlayer() {
		if (Game.getInstance().getTurns() != prevCorrectTurn+1) {
			// the player was not in this room on the previous turn
			System.out.println(notifyMessage);
		}
	}

	/**
	 * Perform the actions associated with following the sequence correctly.
	 * PRECONDITION: This method should only be called if the player has
	 * correctly followed the sequence so far, and successCommand should be
	 * null unless this is the last room in the sequence.
	 * POSTCONDITION: The status of the sequence has been updated to indicate
	 * that the player has correctly followed the sequence so far. If
	 * successCommand is not null, its execute() method has been called
	 * and the sequence has been reset.
	 */
	private void success() {
		notifyPlayer();
		seqFollowed = true;
		// this is the most recent turn in this room in which the player
		// has correctly followed the sequence, so updated prevCorrectTurn
		prevCorrectTurn = Game.getInstance().getTurns();
		if (successCommand != null) {
			successCommand.execute("");
			resetSequence();
		}
	}

	/**
	 * Check if the sequence has been followed correctly so far and update
	 * the status of the sequence accordingly.
	 * POSTCONDITION: If the player has followed the sequence correctly so
	 * far, then success() has been called. Otherwise, the sequence has
	 * been reset.
	 */
	private void checkSequence() {
		int currentTurn = Game.getInstance().getTurns(); // the current turn
		// if the current turn is one greater than the previous correct turn
		// in either the previous room or this one, then the player is
		// correctly following the sequence
		if (currentTurn == prevRoom.getPrevCorrectTurn()+1 || currentTurn
		== prevCorrectTurn + 1) {
			success();
		} else { // otherwise, the player is not correctly following it
			resetSequence();
		}
	}

	/**
	 * If this room is the current room in the game, take the action 
	 * associated with it (if any).
	 * PRECONDITION: This room is the current room in the game.
	 */
	public void act() {
		if (prevRoom == null) {
			// this is the first room in the sequence, so the player
			// has followed the sequence correctly so far
			success();
		} else if (prevRoom.getSeqFollowed()) {
			// the player followed the sequence correctly as far as the
			// previous room, so check if they are continuing to follow it
			checkSequence();
		}
	}
}
