package game;

/**
 * TestArea.java
 *
 * Area to test game mechanics (this area is not used in the final game).
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.Arrays; // for lists
import java.util.Collections; // for collections

public class TestArea extends GameArea {

	/**
	 * Inventory object.
	 */
	private Inventory inventory;
	
	/**
	 * Constructor to initialize rooms, items, etc.
	 */
	public TestArea(Inventory inventory) {

		super();

		this.inventory = inventory; // to allow interaction with the inventory

		Room[] rooms = new GenericRoom[4];
		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = new GenericRoom("Room " + (i+1));
		}

		// to test code related to exits
		rooms[0].addExit("north", new OpenExit(rooms[1], "north"));
		rooms[1].addExit("south", new OpenExit(rooms[0], "south"));
		rooms[1].addExit("east", new OpenExit(rooms[2], "east")); 
		rooms[2].addExit("west", new OpenExit(rooms[1], "west"));
		rooms[2].addExit("south", new OpenExit(rooms[3], "south"));
		rooms[3].addExit("north", new OpenExit(rooms[2], "north"));
		rooms[3].addExit("west", new OpenExit(rooms[0], "west"));
		rooms[0].addExit("east", new OpenExit(rooms[3], "east"));
		Exit secretExit = new OpenExit(rooms[0], "a secret exit", null,
				true);
		rooms[1].addExit("secret exit", secretExit);

		// to test code related to room-specific commands and items
		rooms[0].addCommand("light torches", new MultipleCommand(Arrays.asList(
			new SetExitVisibility(Arrays.asList(secretExit), true),
			new PrintMessage("All hidden exits have been revealed."
				))));
		rooms[0].addItem(new GenericItem("copper key",
			"a mysterious copper key", true));
		rooms[0].addItem(new GenericItem("silver key",
			"a mysterious silver key", true));
		rooms[0].addItem(new GenericItem("lever", "a lever on the wall",
			false));

		Item rake = new GenericItem("rake", "a rake", true);
		rake.setCommand(new Defeat(rake));
		rooms[1].addItem(rake);
		Monster leafMonster = new GameOverMonster("leaf monster",
			"a monster made of leaves", new GenericItem(
			"prize", "the prize you got for defeating the monster", true),
			Arrays.asList(rake), rooms[1], Arrays.asList(secretExit), 3);
		addMonster(leafMonster);
		rooms[0].addExit("inventory exit", new InventoryExit(rooms[1],
			"an exit for testing InventoryExit", Arrays.asList(rake),
			inventory, true));
		rooms[0].addExit("defeat monster exit", new DefeatMonsterExit(
			rooms[2], "an exit for testing DefeatMonsterExit", Arrays.asList(
			leafMonster), true));
		rooms[0].addExit("interval exit", new IntervalExit(rooms[3],
			"an exit for testing Interval Exit", "You hear a door creak open.",
			"You hear a door creak closed.", 5));

		rooms[3].addItem(new MonsterProximityItem("sword", "a sword with " + 
			"a blade that glows when certain monsters are nearby",
			Arrays.asList(leafMonster), "Your sword is glowing."));
		rooms[3].addItem(new TimeLimitItem("candle", "a candle that will " + 
			"burn down over time", "The candle is slowly burning down.",
			"The candle burned out.", 5));

		Room timeLimitRoom = new TimeLimitRoom("a hot, cavernous room",
			"This room is very hot. It isn't safe to spend much time here.",
			new GameOver("Unable to withstand the heat any longer, " +
				"you pass out from exhaustion."), 5);
		rooms[3].addExit("insulated door", new OpenExit(timeLimitRoom,
			"an insulated door"));
		timeLimitRoom.addExit("insulated door", new OpenExit(rooms[3],
			"an insulated door"));

		rooms[0].addCommand("use rake", new InventoryCommand(
			new LimitedUseCommand(
				new MultipleCommand(Arrays.asList(
					new AddExit(rooms[0], "trapdoor",
						new OpenExit(rooms[3], "a trapdoor")),
					new PrintMessage("You have uncovered a trapdoor."))), 1),
			Arrays.asList(rake), inventory, true, "You don't have a rake."));

		// code to test SequenceRoom
		SequenceRoom one = new SequenceRoom("Sequence Room 1",
			"You hear a soft chime echo through the room.");
		SequenceRoom two = new SequenceRoom("Sequence Room 2",
			"You hear a soft chime echo through the room.", one);
		SequenceRoom three = new SequenceRoom("Sequence Room 3",
			"You hear a soft chime echo through the room.", two);
		SequenceRoom four = new SequenceRoom("Sequence Room 4",
			"You hear a soft chime echo through the room.", three,
			new PrintMessage("End of sequence"));
		rooms[0].addExit("start sequence", new OpenExit(one, ""));
		one.addExit("exit 1", new OpenExit(two, ""));
		one.addExit("exit sequence", new OpenExit(rooms[0], ""));
		two.addExit("back to room 1", new OpenExit(one, ""));
		two.addExit("exit 2", new OpenExit(three, ""));
		two.addExit("exit sequence", new OpenExit(rooms[0], ""));
		rooms[0].addExit("sequence room 2", new OpenExit(two, ""));
		three.addExit("exit 3", new OpenExit(four,""));
		three.addExit("back to room 2", new OpenExit(two, ""));

		Game.getInstance().setCurrentRoom(rooms[0]);
		System.out.println("You are in " + rooms[0].getDescription() + ".");
	}
}
