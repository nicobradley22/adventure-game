package game;

/**
 * WaterArea.java
 *
 * The watery area of the game.
 * @author Carrie Snodgrass
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.Arrays; // for lists
import java.util.Collections; // for collections

public class WaterArea extends GameArea {

	/**
	 * Constructor to set up this area of the game.
	 * @param startRoom The room in which the player starts the game.
	 * @param spaceshipPart The spaceship part associated with this area.
	 * @param scubasuit The scubasuit the player needs to navigate this area.
	 * @param inventory The player's inventory.
	 */
	public WaterArea(Room startRoom, Item spaceshipPart, Item scubasuit,
	Inventory inventory) {

		super();

		Room beach = new GenericRoom("You are standing on a sandy beach.");
		startRoom.addExit("beach", new OpenExit(beach, null,
				"You travel across a long gravel path.", true));
		
		//Rooms connected to beach
		Room aboveWater = new GenericRoom("You are floating on the water.");
		Room underWater = new GenericRoom("You are underwater.");
		
		//items in abandoned ship
		Item flute = new GenericItem("flute", "can be used to create music", true);
		
		//a ship with a room sequence
		SequenceRoom abandonedShipDeck = new SequenceRoom("You are standing on the "
				+ "ship deck.", "Everything around you begins to sparkle.");
			SequenceRoom cabin = new SequenceRoom("You are in the Captain's cabin",
				"The ship continues to sparkle.", abandonedShipDeck);
			SequenceRoom belowDeck = new SequenceRoom("You are below deck.",
				"The sparkles persist.", cabin);
			SequenceRoom crowsNest = new SequenceRoom("You are perched in the crow's nest",
				"You become blinded by the sparkles." 
				+ "You open your eyes to see a glittering flute on the deck below",
				belowDeck, new LimitedUseCommand(new AddItem(abandonedShipDeck, flute), 1));
		
		//items on beach
		Item surfboard = new GenericItem("surfboard",
				"surfboard to travel across water", true);
		beach.addItem(surfboard);
		
		//exits to and from beach
		beach.addExit("landing site", new OpenExit(startRoom, null, 
				"You travel back across the long gravel path.", true));
		beach.addExit("across the water", new InventoryExit(
				aboveWater, "You travel across the water.", 
				Arrays.asList(surfboard), inventory, true, 
				"You cannot travel this way. You cannot stay afloat.",
				"You surf the waves.", true));
		aboveWater.addExit("shore", new OpenExit(beach, null, 
				"The water becomes gradually more shallow as you "
				+ "arrive at the shore.", true));
		
		beach.addExit("underwater", new InventoryExit(underWater, "underwater", 
				Arrays.asList(scubasuit), inventory, true,
				"You cannot go underwater as you will not be able to breathe.",
				"You dive into the deep blue sea.", true)); 
		
		
		underWater.addExit("shore", new OpenExit(beach, null,
				"You swim toward the shore", true));
		beach.addExit("abandoned ship", new IntervalExit(abandonedShipDeck, 
				"You approach an abandonded ship.", 
				"The low tide reveals an abandoned ship.",
				"The high tide covers the ship", 6,
				"You cannot access the submerged ship.", 
				"You climb aboard the abandoned ship.", true));
		abandonedShipDeck.addExit("shore", new OpenExit(beach, null, "You return to the shore.", true));
		
		
		//Exits in abandoned ship
		abandonedShipDeck.addExit("creaky stairs", new OpenExit(belowDeck,
				null, "You descend below the deck of the ship", true));
		belowDeck.addExit("creaky stairs", new OpenExit(abandonedShipDeck,
				null, "You return to the deck of the ship.", true));
		abandonedShipDeck.addExit("ladder", new OpenExit(crowsNest, null,
				"You climb up to the crow's nest", true));
		crowsNest.addExit("ladder", new OpenExit(abandonedShipDeck, null,
				"You climb back down to the deck", true));
		abandonedShipDeck.addExit("trapdoor", new OpenExit(cabin, null, 
				"You open the trapdoor to discover a hidden cabin.", true));
		cabin.addExit("trapdoor", new OpenExit(abandonedShipDeck, null,
				"You return through the trapdoor back to the deck.", true));
		cabin.addExit("under the bed", new OpenExit(belowDeck, null, 
				"You crawl beneath the bed to find a hidden staircase.", true));
		belowDeck.addExit("rope", new OpenExit(crowsNest, null,
				"You climb the rope.",true));
		
	
		//above water rooms
		Room island = new GenericRoom("You are on a small island.");
		Room palmTree = new GenericRoom("You are sitting in a palm tree.");

		//above water exits
		Exit aboveWaterToIsland = new OpenExit(island, null,
				"You arrive at a sandbank.", true);
		aboveWater.addExit("sandbank", aboveWaterToIsland);
		
		Exit islandToAboveWater = new OpenExit(aboveWater, null,
				"You surf back into the open sea.", true);
		island.addExit("ocean", islandToAboveWater);
		
		Exit islandToPalmTree = new OpenExit(palmTree, null, 
				"You climb up a palm tree.", true);
		island.addExit("palm tree", islandToPalmTree);
		
		Exit palmTreeToIsland = new OpenExit(island, null, 
				"You climb down the palm tree.", true);
		palmTree.addExit("climb down tree", palmTreeToIsland);
		
		Exit palmTreeToWater = new OpenExit(aboveWater, null, 
				null, false);
		palmTree.addExit("jump into water", palmTreeToWater);
		
		Exit waterToPalmTree = new OpenExit(palmTree, null, null, false);
		aboveWater.addExit("climb up palm tree", waterToPalmTree);
		
		
		//above water items
		Item flashlight = new GenericItem("underwater flashlight", 
				"Illuminates path ahead.",true);
		
		
		// below water rooms 
		Room coralReef = new GenericRoom("You swim into a colorful coral reef.");
		Room bermudaTriangle = new TimeLimitRoom("You swim into nebulous waters.", 
				"WARNING: YOU ARE IN THE BERMUDA TRIANGLE. EXIT IMMEDIATELY.",
				new GameOver("You stayed in the bermuda triangle too long."), 4);
		Room deepSeaFloor = new GenericRoom("You swim out into the dark, deep, sea");
		Room underwaterCave = new GenericRoom("You are in a deep underwater cave "
				+ "surrounded by glowing crystals.");
		
		//exits to and from below water rooms
		underWater.addExit("coral reef", new OpenExit(coralReef, null, 
				"You follow a school of goldfish.", true));
		coralReef.addExit("shallow water", new OpenExit(underWater, null, 
				"You swim away from the bright reef.", true));
		underWater.addExit("deeper underwater",new OpenExit(bermudaTriangle, null,
				"You swim further underwater.", true));
		bermudaTriangle.addExit("shallower water", new OpenExit(underWater, null, 
				"You swim back into shallower water.", true));
		coralReef.addExit("deep sea", new OpenExit(deepSeaFloor, null, 
				"You swim further out to sea", true));
		deepSeaFloor.addExit("coral reef", new OpenExit(coralReef, null,
				"You swim back into lighter water.", true));
		Exit hiddenExit = new OpenExit(underwaterCave, null,
				"You swim into a cave.", false);
		deepSeaFloor.addExit("hidden cave", hiddenExit);
		underwaterCave.addExit("open sea", new OpenExit(deepSeaFloor, null, 
				"You swim out of the crystal cave and back into the sea.", true));

		//items in below water rooms
		Item clue = new GenericItem("signpost", "This signpost reads: "
				+ "Every 6 turns, the tide rolls back to reveal an abandoned ship.",
				false);
		bermudaTriangle.addItem(clue);
		underwaterCave.addItem(spaceshipPart);
		
		//room specific command for deep sea floor
		deepSeaFloor.addCommand("turn on flashlight", 
				new InventoryCommand(
						new SetExitVisibility(Arrays.asList(hiddenExit), true),
				Arrays.asList(flashlight), inventory, true,
				"It is too dark. You are unable to see."));

		//list of exits for siren
		List<Exit> sirenExits = Arrays.asList(
				aboveWaterToIsland, islandToAboveWater, islandToPalmTree,
				palmTreeToIsland, palmTreeToWater, waterToPalmTree);
		
		//monsters
		Monster siren = new GameOverMonster("Siren", 
				"The mythical creature siren.\n"
				+ "She lures prey to permanent sleep when she sings. \n"
				+ "But what if you could make music with her?",
				flashlight, Arrays.asList(flute), island, sirenExits,
				"Your eyelids begin to close as the melody fills your ears.\n",
				"You pull out your flute and accompany the siren. \n"
				+ "Surprised, she stops singing and you live to see another turn", 3);
		addMonster(siren);
		flute.setCommand(new Defeat(flute));
	}
}



