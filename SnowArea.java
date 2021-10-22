package game;

/**
 * SnowArea.java
 *
 * The snowy area of the game.
 * @author Nico Bradley 
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.Arrays; // for lists
import java.util.Collections; // for collections

public class SnowArea extends GameArea {

	/**
	 * Constructor to set up this area of the game.
	 * @param startRoom The room in which the player starts the game.
	 * @param spaceshipPart The spaceship part associated with this area.
	 * @param scubasuit An item needed for WaterArea that will be located
	 * somewhere in this area.
	 * @param wintercoat An item needed for this area.
	 * @param inventory The player's inventory.
	 */
	public SnowArea(Room startRoom, Item spaceshipPart, Item scubasuit,
	Item wintercoat, Inventory inventory) {

		super();
		
		// items in this area
		Item torch = new GenericItem("torch", "A torch", true);
		Item blueKey = new GenericItem("blue key", "A blue key", true);
		Item greenKey = new GenericItem("green key", "A green key", true); 
		Item bagOfFish = new GenericItem("bag of fish", "A bag of fish", true);
		bagOfFish.setCommand(new Defeat(bagOfFish));
		Item frozenPickAxe = new GenericItem("frozen pick axe stuck in the ground", "A pick axe that is barely protruding from "
				+ "the ice.", false);
		Item carrotSnatcher = new GenericItem("carrot snatcher", "A pole with a carrot-shaped extractor on"
				+ " the end", true);
		carrotSnatcher.setCommand(new Defeat(carrotSnatcher));
		Item yetiPoisonJuice = new GenericItem("juice with yeti poison", "A box of juice with yeti poison in it", 
				true);
		yetiPoisonJuice.setCommand(new Defeat(yetiPoisonJuice));

		// entrance rooms
		Room entranceRoom1 = new GenericRoom("You are in a large, cold room. "
				+ "\nTo the north is the tunnel out of the mountain."
				+ "\nTo the west you see a door that has a large carving of a polar bear."
				+ " \nTo the south you see an icy tunnel. \nTo the east, you see another icy tunnel.");
		Room entranceRoom2 = new GenericRoom("You are in a large room. \nYou see an icy tunnel to the south. "
				+ "\nYou also see another icy tunnel to the west.\n"
				+ "To the east, you see a dark chasm, with a thin bridge of ice crossing over the chasm.");
		Room entranceRoom3 = new GenericRoom("You are in a large room. \nYou see an icy tunnel to the west "
				+ "and another one to the north.");
		Room entranceRoom4 = new GenericRoom("You are in a large room surrounded completely by ice. \nTo the south, "
				+ "you see a huge blue door. \nTo the west, you see a huge green door. \nTo the north, you see an icy tunnel"
				+ ". \nTo the east, you see another icy tunnel.");
		
		// spaceship part rooms
		Room spaceshipPartRoom1 = new GenericRoom("You are in a tiny, icy room. \nTo the west, you see a"
				+ " hole in the wall on one side of the room that is covered with snow. \n"
				+ "To the east is an icy tunnel.");
		Room spaceshipPartRoom2 = new GenericRoom("You are in a small, icy room. \n"
				+ "It smells like technology in here.");

		// area1 rooms
		Room area1Room1 = new GenericRoom("You are in a small, icy room. \n"
				+ "To the north, you see a green door. \n"
				+ "To the south, you an icy tunnel.");
		Room area1Room2 = new GenericRoom("You are in a small, icy room. \n"
				+ "To the north, you see an icy tunnel. \n"
				+ "To the east, you see an icy tunnel.\n"
				+ "To the west, you see an icy tunnel.");
		Room area1Room3 = new GenericRoom("You are in a small, icy room. \n"
				+ "There is only one icy tunnel in the room. It goes west.");
		Room area1Room4 = new GenericRoom("You are in a small, icy room.\n"
				+ "There is only one icy tunnel in the room. It goes east.");
		
		// area2 rooms
		Room area2Room1 = new TimeLimitRoom("You are in a small, icy room.\n"
				+ "To the north, you see a blue door.\n"
				+ "To the south, you see an icy tunnel.\n"
				+ "To the east, you see an icy tunnel. \n"
				+ "You also hear the crackling of fire in the distance.",
				"You hear the the ceiling rumble. It sounds like "
				+ "an avalanche is coming soon...", new GameOver("The roof caves "
				+ "in on your head! You are buried in the snow."), 5);
		Room area2Room2 = new TimeLimitRoom("You are in a small, icy room."
				+ "\nTo the west, you see an icy tunnel. \n"
				+ "To the south, you see an icy tunnel."
				+ "\nYou also hear the crackling of fire in the distance.",
				"You hear the the ceiling rumble. It sounds like "
				+ "an avalanche is coming soon...", new GameOver("The roof caves "
				+ "in on your head! You are buried in the snow."), 5);
		Room area2Room3 = new TimeLimitRoom("You are in a small, icy room."
				+ "\n To the north, you see an icy tunnel. \n"
				+ "To the east, you see an icy tunnel."
				+ "\nYou also hear the crackling of fire in the distance.",
				"You hear the the ceiling rumble. It sounds like "
				+ "an avalanche is coming soon...", new GameOver("The roof caves "
				+ "in on your head! You are buried in the snow."), 5);
		Room area2Room4 = new TimeLimitRoom("You are in a small, icy room."
				+ "\nTo the north, you see an icy tunnel."
				+ "\nTo the west, you see an icy tunnel."
				+ "\nYou see a torch hanging on the wall.",
				"You hear the the ceiling rumble. It sounds like "
				+ "an avalanche is coming soon...", new GameOver("The roof caves "
				+ "in on your head! You are buried in the snow."), 5);
		
		// scubasuit rooms
		Room scubasuitRoom1 = new GenericRoom("You are in a small, icy room.\n"
				+ "To the northeast is an icy tunnel.\n"
				+ "To the southeast is another icy tunnel.\n"
				+ "It smells like chlorine in here.");
		Room scubasuitRoom2 = new GenericRoom("You are in a small, icy room.\n"
				+ "To the south is an icy tunnel.\n"
				+ "To the southwest is another icy tunnel.\n"
				+ "It smells like chlorine in here.");
		Room scubasuitRoom3 = new GenericRoom("You are in a small, icy room.\n"
				+ "To the north is an icy tunnel.\n"
				+ "To the northwest is another icy tunnel.\n"
				+ "It smells like chlorine in here.");
		
		// adding items to rooms
		entranceRoom2.addItem(bagOfFish);
		entranceRoom3.addItem(greenKey);
		entranceRoom3.addItem(frozenPickAxe);
		scubasuitRoom3.addItem(scubasuit);
		spaceshipPartRoom2.addItem(spaceshipPart);
		area1Room1.addItem(carrotSnatcher);
		area1Room4.addItem(yetiPoisonJuice);
		area2Room4.addItem(torch);
		
		// exits that monsters need
		Exit A2R1toA2R2 = new OpenExit(area2Room2, "east tunnel", "You walk through the tunnel...", true);
		Exit A2R1toA2R3 = new OpenExit(area2Room3, "south tunnel, ", "You walk through the tunnel...", true);
		Exit A2R2toA2R1 = new OpenExit(area2Room1, "west tunnel", "You walk through the tunnel...", true);
		Exit A2R2toA2R4 = new OpenExit(area2Room4, "south tunnel", "You walk through the tunnel...", true);
		Exit A2R3toA2R1 = new OpenExit(area2Room1, "north tunnel", "You walk through the tunnel...", true);
		Exit A2R3toA2R4 = new OpenExit(area2Room4, "east tunnel", "You walk through the tunnel...", true);
		Exit A2R4toA2R2 = new OpenExit(area2Room2, "north tunnel", "You walk through the tunnel...", true);
		Exit A2R4toA2R3 = new OpenExit(area2Room3, "west tunnel", "You walk through the tunnel...", true);
		
		Exit A1R2toA1R3 = new OpenExit(area1Room3, "west tunnel", "You walk through the tunnel...", true);
		Exit A1R2toA1R4 = new OpenExit(area1Room4, "east tunnel", "You walk through the tunnel...", true);
		Exit A1R3toA1R2 = new OpenExit(area1Room2, "east tunnel", "You walk through the tunnel...", true);
		Exit A1R4toA1R2 = new OpenExit(area1Room2, "west tunnel", "You walk through the tunnel...", true);
		
		Exit SR1toSR2 = new OpenExit(scubasuitRoom2, "northeast tunnel", "You walk through the tunnel...", true);
		Exit SR1toSR3 = new OpenExit(scubasuitRoom3, "southeast tunnel", "You walk through the tunnel...", true);
		Exit SR2toSR1 = new OpenExit(scubasuitRoom1, "southwest tunnel", "You walk through the tunnel...", true);
		Exit SR2toSR3 = new OpenExit(scubasuitRoom3, "south tunnel", "You walk through the tunnel...", true);
		Exit SR3toSR1 = new OpenExit(scubasuitRoom1, "northwest tunnel", "You walk through the tunnel...", true);
		Exit SR3toSR2 = new OpenExit(scubasuitRoom2, "north tunnel", "You walk through the tunnel...", true);
		
		// monsters in this area - 
		Monster polarBear = new TeleportMonster("hungry polar bear", "a large, hungry-looking polar bear",
				null, Arrays.asList(bagOfFish), area2Room3, Arrays.asList(A2R1toA2R2, A2R1toA2R3, A2R2toA2R1, A2R2toA2R4, 
						A2R3toA2R1, A2R3toA2R4, A2R4toA2R2, A2R4toA2R3), "The hungry polar bear races towards you"
						 		+ "and hits you so hard you fly back to your spaceship!", "The polar bear gobbles"
						 				+ " down the fish! \nIn the distance you hear the creaking of a door opening...",
						 				5, new Teleport(new Travel(), startRoom));
		Monster snowman = new GameOverMonster("scary snow man with a huge carrot for his nose",
				"a scary snowman with a large carrot nose",
				 blueKey, Arrays.asList(carrotSnatcher), area1Room2, Arrays.asList(A1R2toA1R3, A1R2toA1R4, 
						 A1R3toA1R2, A1R4toA1R2), "The snowman takes his carrot out of his nose"
						 		+ "and whacks you. You pass out.", "Without his carrot the snowman crumples to the ground!"
						 				+ "You've defeated him!", 5);
		
		Monster yeti = new TeleportMonster("scary, thirsty, yeti", "a scary, thirsty yeti", null,
				Arrays.asList(yetiPoisonJuice), scubasuitRoom3, Arrays.asList(SR1toSR2, SR1toSR3, SR2toSR1, SR2toSR3, SR3toSR1, SR3toSR2), 
				"The yeti winds his arm back and punches you in the face!\nYou black out.",
				"The yeti slurps down the juice!\nThe yeti poison immediatey kicks in and he falls to the ground!", 5, new Teleport(new Travel(), startRoom));
		
		// startRoom exit
		startRoom.addExit("cave in snowy mountain", new InventoryExit(entranceRoom1, "cave in ice mountain", Arrays.asList(wintercoat), inventory, true, "It's too cold to go in there!", "You enter the mountain...", true));
		
		// entranceRoom1 exits
		entranceRoom1.addExit("north tunnel", new OpenExit(startRoom, "north tunnel", "You walk through the tunnel...", true));
		entranceRoom1.addExit("polar bear door", 
				new DefeatMonsterExit(spaceshipPartRoom1, 
				"hole in the wall covered with fragile ice", 
				Arrays.asList(polarBear), true, "This polar bear door is locked!", "You open the polar bear door and walk through!", true));
		entranceRoom1.addExit("east tunnel", new OpenExit(entranceRoom2, "east tunnel", "You walk through the tunnel...", true));
		entranceRoom1.addExit("south tunnel", new OpenExit(entranceRoom4, "south tunnel", "You walk through the tunnel...", true));

		// entranceRoom2 exits
		entranceRoom2.addExit("west tunnel", new OpenExit(entranceRoom1, "west tunnel", "You walk through the tunnel...", true));
		entranceRoom2.addExit("south tunnel", new OpenExit(entranceRoom3, "south tunnel", "You walk through the tunnel...", true));
		entranceRoom2.addExit("ice bridge", new OpenExit(scubasuitRoom1, "ice bridge", "You cross the ice bridge...", true));
		
		// entranceRoom3 exits
		entranceRoom3.addExit("north tunnel", new OpenExit(entranceRoom2, "north tunnel", "You walk through the tunnel...", true));
		entranceRoom3.addExit("west tunnel", new OpenExit(entranceRoom4, "west tunnel", "You walk through the tunnel...", true));
		
		// entranceRoom4 exits
		entranceRoom4.addExit("north tunnel", new OpenExit(entranceRoom1, "north tunnel", "You walk through the tunnel...", true));
		entranceRoom4.addExit("east tunnel", new OpenExit(entranceRoom3, "east tunnel", "You walk through the tunnel...", true));
		entranceRoom4.addExit("blue door", new InventoryExit(area2Room1, "blue door", Arrays.asList(blueKey), inventory, true, "The door is locked shut!",
				"You walk through the door...", true));
		entranceRoom4.addExit("green door", new InventoryExit(area1Room1, "green door", Arrays.asList(greenKey), inventory, true, "The door is locked shut!",
				"You walk through the green door...", true));
		
		// spaceshipPartRoom1 exits
		spaceshipPartRoom1.addExit("east tunnel", new OpenExit(entranceRoom1, "east tunnel", "You walk through the tunnel...", true));
		Exit SPR1toSPR2 = new OpenExit(spaceshipPartRoom2, "hole in wall", "You walk through the hole...", false);
		spaceshipPartRoom1.addExit("hole in wall", SPR1toSPR2);
				
		// spaceshipPartRoom2 exit
		spaceshipPartRoom2.addExit("east tunnel", new OpenExit(spaceshipPartRoom1, "east tunnel", "You walk through the tunnel...", true));

		// area1Room1 exits
		area1Room1.addExit("green door", new OpenExit(entranceRoom4, "green door", "You open the door and walk through...", true));
		area1Room1.addExit("south tunnel", new OpenExit(area1Room2, "south tunnel", "You walk through the tunnel ...", true));
		
		// area1Room2 exits
		area1Room2.addExit("north tunnel", new OpenExit(area1Room1, "north tunnel"));
		area1Room2.addExit("east tunnel", A1R2toA1R3);
		area1Room2.addExit("west tunnel", A1R2toA1R4);

		// area1Room3 exit
		area1Room3.addExit("west tunnel", A1R3toA1R2);
		
		// area1Room4 exit
		area1Room4.addExit("east tunnel", A1R4toA1R2);
		
		// area2Room1 exits
		area2Room1.addExit("blue door", new OpenExit(entranceRoom4, "blue door", "You open the door and walk through...", true));
		area2Room1.addExit("south tunnel", A2R1toA2R3);
		area2Room1.addExit("east tunnel", A2R1toA2R2);
		
		// area2Room2 exits
		area2Room2.addExit("west tunnel", A2R2toA2R1);
		area2Room2.addExit("south tunnel", A2R2toA2R4);
		
		// area2Room3 exits
		area2Room3.addExit("north tunnel", A2R3toA2R1);
		area2Room3.addExit("east tunnel", A2R3toA2R4);
		
		// area2Room4 exits
		area2Room4.addExit("north tunnel", A2R4toA2R2);
		area2Room4.addExit("west tunnel", A2R4toA2R3);
		
		// scubasuitRoom1 exits
		scubasuitRoom1.addExit("northeast tunnel", SR1toSR2);
		scubasuitRoom1.addExit("southeast tunnel", SR1toSR3);
		scubasuitRoom1.addExit("ice bridge", new OpenExit(entranceRoom2, "ice bridge", "You cross the ice bridge...", true));

		// scubasuitRoom1 exits
		scubasuitRoom2.addExit("southwest tunnel", SR2toSR1);
		scubasuitRoom2.addExit("south tunnel", SR2toSR3);

		// scubasuitRoom1 exits
		scubasuitRoom3.addExit("northwest tunnel", SR3toSR1);
		scubasuitRoom3.addExit("north tunnel", SR3toSR2);

		// adding monsters
		addMonster(snowman);
		addMonster(polarBear);
		addMonster(yeti);
		
		// room specific commands
		spaceshipPartRoom1.addCommand("melt snow", 
				new InventoryCommand(
						new SetExitVisibility(Arrays.asList(SPR1toSPR2), true),
				Arrays.asList(torch), inventory, true,
				"You can't melt the snow! If only you had some fire..."));

	}

}

