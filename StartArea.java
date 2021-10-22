package game;

/**
 * StartArea.java
 *
 * The starting area of the game (which also serves as a central hub
 * connecting all of the other areas).
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.Arrays; // for lists
import java.util.Collections; // for collections

public class StartArea extends GameArea {

	/**
	 * Constructor to set up this area of the game.
	 * @param startRoom The room in which the player starts the game.
	 * @param spaceshipParts The parts of the spaceship.
	 * @param inventory The player's inventory.
	 */
	public StartArea(Room startRoom, Item[] spaceshipParts,
	Inventory inventory) {

		super();

		// rooms and room-specific commands
		Room airlock = new InitialCommandRoom(
			"You are in the airlock of your spaceship.",
			new CustomizeCommand("Most of the power in your spaceship has " +
			"been diverted for repairs,\nbut the teleport is still fully " +
			"functional.\nYou can set up a command to trigger teleportation " +
			"back to your spaceship.", new MultipleCommand(Arrays.asList(
			new PrintMessage("The world dissolves around you as you are " +
			"teleported back to your spaceship."),
			new Teleport(new Travel(), startRoom)))));

		Room controlRoom = new InitialCommandRoom(
			"You are in the control room of the spaceship.", new PrintMessage(
			"Now that you have all of the materials needed to repair " +
			"your spaceship,\nthe spaceship has initiated self-repair.\n" +
			"In a few minutes, the spaceship will be ready for take-off.\n" +
			"When you are ready, enter the command \"take off\" to initiate " +
			"departure."));
		controlRoom.addCommand("take off", new GameOver(
			"Your spaceship takes off with a roar.\nAs you take off, " +
			"you look back at the planet one more time,\nremembering " +
			"all your adventures there.\nThen you face forward again, " +
			"ready for your next adventure.\nCongratulations, you have " +
			"successfully completed the game!\nThis game was created by " +
			"Nico Bradley, Carrie Snodgrass, and Claire Wagner.\nThanks " +
			"for playing!"));
		
		// exits
		startRoom.addExit("spaceship", new OpenExit(airlock,
			"the spaceship in which you crashed on this planet"));
		airlock.addExit("outer door", new OpenExit(startRoom, "the outer " +
			"door of the airlock, which leads outside the spaceship"));
		airlock.addExit("inner door", new InventoryExit(controlRoom,
			"the inner door of the airlock, which leads to the control room",
			Arrays.asList(spaceshipParts), inventory, true,
			"Until you have all of the parts needed to repair your " +
			"spaceship,\nit's not safe to go inside.",
			"Now that you have all of the parts need to repair your " +
			"spaceship,\nit's safe to go inside. The door slowly slides " +
			"open, and you walk through.", true));

	}

}

