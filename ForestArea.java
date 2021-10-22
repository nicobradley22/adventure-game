package game;

/**
 * ForestArea.java
 *
 * The forested area of the game.
 *
 * @author Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.ArrayList; // for lists
import java.util.Arrays; // for lists
import java.util.Collections; // for collections

public class ForestArea extends GameArea {

	/**
	 * Constructor to set up this area of the game.
	 * @param startRoom The room in which the player starts the game.
	 * @param spaceshipPart The spaceship part associated with this area.
	 * @param inventory The player's inventory.
	 * @param wintercoat An item needed for SnowArea that will be located
	 * somewhere in this area.
	 */
	public ForestArea(Room startRoom, Item spaceshipPart, Item wintercoat,
	Inventory inventory) {

		super();

		/* rooms and relevant room-specific commands */

		Room forestClearing = new GenericRoom("You are in a clearing in the " +
			"trees. A small stream runs through the clearing.\n" +
			"At one end of the clearing is a deep hole.");
		forestClearing.addCommand("jump", new GameOver(
			"You jump down the hole.\nIt is a long way down, " +
			"and you black out when you hit the bottom."));

		Room waterfallRoom = new GenericRoom("Ahead of you, the stream " +
			"plunges down the side of a steep cliff.\nAt the base of the " +
			"waterfall, you see a cracked wall.");

		Room waterfallCave = new GenericRoom(
			"You are inside a mist-filled cave at the base of the waterfall.");

		Room lakeRoom = new GenericRoom(
			"You are by the shore of a clear lake.\n" +
			"On one side of the lake, you see a ruined building.\n" +
			"On the other side, you see the entrance to a partially " +
			"submerged cave.");

		Room floodedCave = new TimeLimitRoom(
			"You are in a narrow cave full of water.\n" +
			"The water level slowly rises and falls.",
			"The water level is rising. If you stay here too long, " +
			"you will drown.", new GameOver("The water has filled " +
			"the entire cave.\nYou try to swim to safety, but you can't " +
			"make it."), 5);

		SequenceRoom sequence1 = new SequenceRoom(
			"You are in the courtyard of the ruined building.\n" +
			"On one side of the room is a door made of iron.\n" +
			"On the other side is a door made of silver.\n" +
			"There is a patch of soft soil in one corner of the room.",
			"You hear a soft chime echoing through the room.");

		SequenceRoom sequence2 = new SequenceRoom(
			"You are in a small room.\n" +
			"On one side of the room is a door made of iron.\n" +
			"On the other side is a door made of copper.",
			"You hear a soft chime echoing through the room.", sequence1);

		SequenceRoom sequence3 = new SequenceRoom(
			"You are in a small room.\n" +
			"On one side of the room is a door made of copper.\n" +
			"On the other side is a door made of gold.",
			"You hear a soft chime echoing through the room.", sequence2);

		// this is the last room in the sequence, so LimitedUseCommand
		// is being used here to prevent the item from reappearing if
		// the player completes the sequence more than once
		SequenceRoom sequence4 = new SequenceRoom(
			"You are in a small room.\n" +
			"On one side of the room is a door made of gold.\n" +
			"On the other side is a door made of silver.\n" +
			"There is a patch of soft soil in one corner of the room.",
			"You hear a soft chime echoing through the room.", sequence3);
		sequence4.setSuccessCommand(new LimitedUseCommand(new MultipleCommand(
			Arrays.asList(new PrintMessage(
			"A treasure chest containing a winter coat appears in the " +
			"center of the room."), new AddItem(sequence4, wintercoat))), 1));

		Room torchRoom = new InitialCommandRoom(
			"You are in a cave lit by flickering torches.\n" +
			"On one side of the room is a metal door.\n" +
			"On the other side is a door covered with ice.",
			new MultipleCommand(Arrays.asList(new PrintMessage(
			"Suddenly, rocks fall and block off the passage behind you!"),
			new RemoveExit(waterfallCave, "deeper into cave"))));

		Room fireRoom = new GenericRoom(
			"You are in a rough cave hewn out of the rock.\n" +
			"There is a wall of fire near the back of the room.");

		Room hotRoom = new TimeLimitRoom(
			"You are in a hot, cavernous room.\n" +
			"At one end of the room is a metal door.\n" +
			"At the other end is a door covered with vines.",
			"This room is very hot. It isn't safe to spend much time here.",
			new GameOver("Unable to withstand the heat any longer, " +
				"you pass out from exhaustion."), 5);

		Room ladderRoom = new GenericRoom("You are in a dank, damp cave.\n" +
			"There is an rusty lever on the floor.");

		Room shovelRoom = new GenericRoom(
			"You are in a cave with a sandy floor.");


		/* items and related room- and item-specific commands */

		Item shovel = new GenericItem("shovel", "a rusty iron shovel", true);
		shovelRoom.addItem(shovel);

		Item hammer = new GenericItem("hammer",
			"a heavy iron sledgehammer", true);
		hammer.setCommand(new Defeat(hammer));
		floodedCave.addItem(hammer);

		// note: sword gets an empty list of monsters to which it should
		// react because we still need to instantiate those monsters
		MonsterProximityItem sword = new MonsterProximityItem("sword",
			"a sword with a blade that reacts to the proximity of monsters.",
			new ArrayList<Monster>(4), "The blade of your sword is glowing.");
		sword.setCommand(new Defeat(sword));
		hotRoom.addItem(sword);

		Item longTorch = new TimeLimitItem("long torch",
			"a long torch soaked in pitch", "Your torch is burning down.",
			"Your torch has burned out.", 5);
		torchRoom.addItem(longTorch);
		
		Item shortTorch = new TimeLimitItem("short torch",
			"a short torch soaked in pitch", "Your torch is burning down.",
			"Your torch has burned out.", 3);
		torchRoom.addItem(shortTorch);

		Item rustyLever = new GenericItem("rusty lever",
			"a lever that is rusted so fast that it can only be moved\n" +
			"by a heavy object", false);
		ladderRoom.addItem(rustyLever);

		// note: firespear gets an empty list of incompatible items because
		// we still need to instantiate the icespear, but before we can
		// instantiate the icespear, we need to instantiate the firespear
		IncompatibleItem firespear = new IncompatibleItem("firespear",
			"a spear whose tip glows with the power of fire",
			new ArrayList<Item>(1), "You can't carry the firespear and " +
			"the icespear at the same time.", inventory);
		firespear.setCommand(new Defeat(firespear));
		waterfallCave.addItem(firespear);

		IncompatibleItem icespear = new IncompatibleItem("icespear",
			"a spear whose tip glows with the power of ice",
			Arrays.asList(firespear), "You can't carry the firespear and" +
			"the icespear at the same time.", inventory);
		firespear.addIncompatibleItem(icespear);
		icespear.setCommand(new Defeat(icespear));
	
		Item bottle = new GenericItem("bottle", "a small glass bottle", true);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		sequence1.addCommand("dig", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new AddItem(sequence1,
			bottle), new RemoveRoomCommand(sequence1, "dig"),
			new PrintMessage("You uncover a small glass bottle."))),
			Arrays.asList(shovel), inventory, true, "With what?"));

		Item water = new DependentItem("water", "water", Arrays.asList(bottle),
			"You can't carry this unless you have something to put it in.",
			inventory, true);
		lakeRoom.addItem(water);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		fireRoom.addCommand("extinguish fire", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new AddItem(fireRoom,
			spaceshipPart), new RemoveRoomCommand(fireRoom, "extinguish fire"),
			new PrintMessage("You pour the water on the flames, " +
			"and they slowly flicker out.\nWhere the wall of fire " +
			"used to be, you see a treasure chest containing\n" +
			spaceshipPart.getName() + ". You can use this to repair your " +
			"spaceship!"))), Arrays.asList(water), inventory, true,
			"With what?"));

		Item copperKey = new GenericItem("copper key",
			"a tarnished copper key", true);

		Item ironKey = new GenericItem("iron key",
			"a heavy iron key", true);

		Item silverKey = new GenericItem("silver key",
			"an intricate silver key", true);
		
		Item goldKey = new GenericItem("gold key",
			"a tiny golden key", true);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		sequence4.addCommand("dig", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new AddItem(sequence4,
			goldKey), new RemoveRoomCommand(sequence4, "dig"),
			new PrintMessage("You uncover a tiny golden key."))),
			Arrays.asList(shovel), inventory, true, "With what?"));


		/* exits and related room-specific commands */

		Exit startToClearing = new OpenExit(forestClearing,
			"a narrow path winding into the forest",
			"You walk along the path into the forest until you reach " +
			"a clearing.", true);
		startRoom.addExit("forest path", startToClearing);

		Exit clearingToStart = new OpenExit(startRoom,
			"a narrow path winding out of the forest",
			"You walk along the path until you come out of the forest.", true);
		forestClearing.addExit("forest path", clearingToStart);

		Exit clearingToWaterfall = new OpenExit(waterfallRoom,
			"the stream flowing down into the clearing",
			"You walk upstream.", true);
		forestClearing.addExit("upstream", clearingToWaterfall);

		Exit waterfallToClearing = new OpenExit(forestClearing,
			"the stream flowing down into the forest",
			"You walk downstream.", true);
		waterfallRoom.addExit("downstream", waterfallToClearing);

		Exit waterfallRoomToCave = new OpenExit(waterfallCave,
			"the opening to a small cave",
			"You walk through the waterfall and into the cave.", true);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		waterfallRoom.addCommand("smash wall", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new PrintMessage(
			"You smash the wall with the hammer.\n" +
			"You have uncovered the entrance to a cave."),
			new AddExit(waterfallRoom, "cave", waterfallRoomToCave),
			new RemoveRoomCommand(waterfallRoom,
			"smash wall"))), Arrays.asList(hammer), inventory, true,
			"With what?"));

		Exit waterfallCaveToRoom = new OpenExit(waterfallRoom,
			"the waterfall outside the cave entrance",
			"You walk out of the cave and through the waterfall.", true);
		waterfallCave.addExit("waterfall", waterfallCaveToRoom);

		Exit clearingToLake = new OpenExit(lakeRoom,
			"the stream flowing out of the clearing",
			"You follow the stream down to a small, clear lake.", true);
		forestClearing.addExit("downstream", clearingToLake);

		Exit lakeToClearing = new OpenExit(forestClearing,
			"the stream flowing down into the lake",
			"You walk upstream.", true);
		lakeRoom.addExit("upstream", lakeToClearing);

		Exit lakeToFloodedCave = new IntervalExit(floodedCave,
			"the entrance to a partially submerged cave",
			"The water rushes out of the cave.",
			"The water rushes into the cave.", 5,
			"The water level is too high for you to swim through.",
			"The water level has dropped enough for you to swim through.\n" +
			"You swim through the cave entrance.", true);
		lakeRoom.addExit("cave", lakeToFloodedCave);

		ManualExit floodedCaveToLake = new ManualExit(lakeRoom,
			"the exit out of the cave",
			"The exit has collapsed behind you. You can't get through.",
			"You swim out of the hole that you made in the wall of the cave.",
			true);
		floodedCave.addExit("exit", floodedCaveToLake);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		floodedCave.addCommand("smash wall", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new ManualExitCommand(
			floodedCaveToLake, true), new RemoveRoomCommand(
			floodedCave, "smash wall"), new PrintMessage("You smash through " +
			"the wall and create an opening that you can swim through."))),
			Arrays.asList(hammer), inventory, true, "With what?"));

		// this is an InventoryExit to avoid softlocking the game
		Exit caveToTorchRoom = new InventoryExit(torchRoom,
			"a steep passage leading downwards",
			Arrays.asList(hammer), inventory, true,
			"You have a bad feeling about leaving your hammer behind...",
			"You go through the passage.", true);
		waterfallCave.addExit("deeper into cave", caveToTorchRoom);
	
		Exit torchRoomToHotRoom = new OpenExit(hotRoom,
			"a metal door", "You push the door open and go in.", true);
		torchRoom.addExit("metal door", torchRoomToHotRoom);

		Exit hotRoomToTorchRoom = new OpenExit(torchRoom,
			"a metal door", "You push the door open and go in.", true);
		hotRoom.addExit("metal door", hotRoomToTorchRoom);

		Exit ladderToClearing = new OpenExit(forestClearing,
			"a ladder that leads up out of the cave",
			"You climb up the ladder.", true);
		Exit clearingToLadderRoom = new OpenExit(ladderRoom,
			"a ladder that leads down into a cave",
			"You climb down the ladder.", true);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		ladderRoom.addCommand("hit lever", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new PrintMessage(
			"You hit the lever with your hammer, and a ladder falls down " +
			"from the ceiling."), new AddExit(ladderRoom,
			"ladder", ladderToClearing), new AddExit(forestClearing,
			"ladder", clearingToLadderRoom), new RemoveRoomCommand(ladderRoom,
			"hit lever"))), Arrays.asList(hammer), inventory, true,
			"No item in your inventory is heavy enough to move the lever."));

		Exit hotRoomToLadderRoom = new InventoryExit(ladderRoom,
			"a door that is thickly covered with vines", Arrays.asList(sword),
			inventory, true, "The vines are blocking the door.",
			"You use your sword to cut through the vines, " +
			"and then you go through the door.", true);
		hotRoom.addExit("overgrown door", hotRoomToLadderRoom);

		Exit ladderToHotRoom = new InventoryExit(hotRoom,
			"a door that is thickly covered with vines", Arrays.asList(sword),
			inventory, true, "The vines are blocking the door.",
			"You use your sword to cut through the vines, " +
			"and then you go through the door.", true);
		ladderRoom.addExit("overgrown door", ladderToHotRoom);

		// an exit that is intraversable and will be replaced by
		// traversableLadderToShovelRoom once the relevant command
		// is executed
		Exit intraversableLadderToShovelRoom = new ManualExit(shovelRoom,
			"a wooden door that looks highly flammable",
			"The door is locked, and you can't open it.", "", true);
		ladderRoom.addExit("wooden door", intraversableLadderToShovelRoom);

		Exit traversableLadderToShovelRoom = new OpenExit(shovelRoom,
			"the remains of a burned wooden door",
			"You walk through the doorway.", true);
		// note: RemoveRoomCommand is being used here to prevent the command
		// from being used more than once
		ladderRoom.addCommand("burn wooden door", new InventoryCommand(
			new MultipleCommand(Arrays.asList(new AddExit(ladderRoom,
			"doorway", traversableLadderToShovelRoom), new RemoveExit(
			ladderRoom, "wooden door"), new RemoveRoomCommand(ladderRoom,
			"burn wooden door"), new PrintMessage(
			"You set fire to the door with your torch, and it burns " +
			"to ashes."))), Arrays.asList(shortTorch, longTorch),
			inventory, false, "With what?"));

		Exit shovelToLadderRoom = new OpenExit(ladderRoom,
			"the remains of a burned wooden door",
			"You walk through the doorway.", true);
		shovelRoom.addExit("doorway", shovelToLadderRoom);

		// this exit is instantiated later because it requires references
		// to the relevant monster(s)
		DefeatMonsterExit torchToFireRoom;

		Exit fireToTorchRoom = new OpenExit(torchRoom,
			"a door covered by ice",
			"Now that the ice monster has been defeated,\n" +
			"the ice has melted enough for you to open the door.\n" +
			"You open the door and go through.", true);
		fireRoom.addExit("icy door", fireToTorchRoom);

		Exit lakeToSequence1 = new OpenExit(sequence1,
			"the door to the ruined building",
			"You open the door and enter the ruined building.", true);
		lakeRoom.addExit("ruined building", lakeToSequence1);

		Exit sequence1ToLake = new OpenExit(lakeRoom,
			"the door that leads out of the ruined building",
			"You open the door and go out of the ruined building.", true);
		sequence1.addExit("door outside", sequence1ToLake);
		
		Exit sequence1To2 = new InventoryExit(sequence2,
			"a door made of iron", Arrays.asList(ironKey),
			inventory, true, "The door is locked.",
			"You use the iron key to unlock the door and go through.", true);
		sequence1.addExit("iron door", sequence1To2);
			
		Exit sequence2To1 = new InventoryExit(sequence1,
			"a door made of iron", Arrays.asList(ironKey),
			inventory, true, "The door is locked.",
			"You use the iron key to unlock the door and go through.", true);
		sequence2.addExit("iron door", sequence2To1);

		Exit sequence2To3 = new InventoryExit(sequence3,
			"a door made of copper", Arrays.asList(copperKey),
			inventory, true, "The door is locked.",
			"You use the copper key to unlock the door and go through.", true);
		sequence2.addExit("copper door", sequence2To3);
			
		Exit sequence3To2 = new InventoryExit(sequence2,
			"a door made of copper", Arrays.asList(copperKey),
			inventory, true, "The door is locked.",
			"You use the copper key to unlock the door and go through.", true);
		sequence3.addExit("copper door", sequence3To2);

		Exit sequence3To4 = new InventoryExit(sequence4,
			"a door made of gold", Arrays.asList(goldKey),
			inventory, true, "The door is locked.",
			"You use the gold key to unlock the door and go through.", true);
		sequence3.addExit("gold door", sequence3To4);
			
		Exit sequence4To3 = new InventoryExit(sequence3,
			"a door made of gold", Arrays.asList(goldKey),
			inventory, true, "The door is locked.",
			"You use the gold key to unlock the door and go through.", true);
		sequence4.addExit("gold door", sequence4To3);

		Exit sequence4To1 = new InventoryExit(sequence1,
			"a door made of silver", Arrays.asList(silverKey),
			inventory, true, "The door is locked.",
			"You use the silver key to unlock the door and go through.", true);
		sequence4.addExit("silver door", sequence4To1);
			
		Exit sequence1To4 = new InventoryExit(sequence4,
			"a door made of silver", Arrays.asList(silverKey),
			inventory, true, "The door is locked.",
			"You use the silver key to unlock the door and go through.", true);
		sequence1.addExit("silver door", sequence1To4);

		
		/* invisible exits that only monsters can use */

		Exit invisibleSequence1To3 = new MonsterExit(sequence3);
		sequence1.addExit("monster exit", invisibleSequence1To3);

		Exit invisibleSequence3To1 = new MonsterExit(sequence1);
		sequence3.addExit("monster exit", invisibleSequence3To1);

		Exit invisibleSequence2To4 = new MonsterExit(sequence4);
		sequence2.addExit("monster exit", invisibleSequence2To4);

		Exit invisibleSequence4To2 = new MonsterExit(sequence2);
		sequence4.addExit("monster exit", invisibleSequence4To2);

		Exit invisibleLakeToWaterfallCave = new MonsterExit(waterfallCave);
		lakeRoom.addExit("monster exit", invisibleLakeToWaterfallCave);

		Exit invisibleWaterfallCaveToLake = new MonsterExit(lakeRoom);
		waterfallCave.addExit("monster exit", invisibleWaterfallCaveToLake);

		Exit invisibleLakeToWaterfallRoom = new MonsterExit(waterfallRoom);
		lakeRoom.addExit("monster exit", invisibleLakeToWaterfallRoom);

		Exit invisibleWaterfallRoomToLake = new MonsterExit(lakeRoom);
		waterfallRoom.addExit("monster exit", invisibleWaterfallRoomToLake);

		Exit invisibleHotToShovelRoom = new MonsterExit(shovelRoom);
		hotRoom.addExit("monster exit", invisibleHotToShovelRoom);

		Exit invisibleShovelToHotRoom = new MonsterExit(hotRoom);
		shovelRoom.addExit("monster exit", invisibleShovelToHotRoom);

		Exit invisibleTorchToShovelRoom = new MonsterExit(shovelRoom);
		torchRoom.addExit("monster exit", invisibleTorchToShovelRoom);

		Exit invisibleShovelToTorchRoom = new MonsterExit(torchRoom);
		shovelRoom.addExit("monster exit", invisibleShovelToTorchRoom);


		/* code related to monsters */
		
		// exits that vineMonster and rockMonster both use
		List<Exit> vineAndRockMonsterExits = Arrays.asList(
			lakeToClearing, clearingToLake, clearingToWaterfall,
			waterfallToClearing, waterfallCaveToRoom, waterfallRoomToCave,
			invisibleLakeToWaterfallRoom, invisibleWaterfallRoomToLake,
			invisibleLakeToWaterfallCave, invisibleWaterfallCaveToLake);
		
		Monster vineMonster = new TeleportMonster("vine monster",
			"a monster made entirely of vines.\nIf only you could " +
			"cut the vines with something..", silverKey,
			Arrays.asList(sword), lakeRoom, vineAndRockMonsterExits,
			"The monster wraps its vines around you and pulls you into the " +
			"air.\nYou lose consciousness.", "You cut apart the vines " +
			"with your sword.\nFrightened, the monster escapes into the " +
			"ground.\nYou have defeated the monster!", 3, new Teleport(
			new Travel(), startRoom));
		addMonster(vineMonster);

		Monster rockMonster = new GameOverMonster("rock monster",
			"a monster made of living rock.\nYou need a heavy weapon " +
			"to defeat this monster", ironKey, Arrays.asList(
			hammer), forestClearing, vineAndRockMonsterExits,
			"The monster pulls you underground, and you lose consciousness.",
			"You attack the monster with your hammer,\nand it disintegrates " +
			"into a pile of gravel.\nYou have defeated the monster!", 5);
		addMonster(rockMonster);

		Monster fireMonster = new GameOverMonster("fire monster",
			"a monster made of living fire.\nThis monster is weak to " +
			"weapons that contain the power of ice", copperKey,
			Arrays.asList(icespear), sequence1, Arrays.asList(sequence1To2,
			sequence2To1, sequence2To3, sequence3To2, sequence3To4,
			sequence4To3, sequence4To1, invisibleSequence1To3,
			invisibleSequence3To1, invisibleSequence2To4,
			invisibleSequence4To2),
			"The fire monster has surrounded you with a wall of fire.\n" +
			"Unable to withstand the heat, you lose consciousness.",
			"You attack the monster with your firespear, and it vanishes " +
			"in a cloud of steam.\nYou have defeated the monster!", 4);
		addMonster(fireMonster);

		Monster iceMonster = new GameOverMonster("ice monster",
			"a monster made of living ice.\nThis monster is weak to " +
			"weapons that contain the power of fire", icespear,
			Arrays.asList(firespear), torchRoom, Arrays.asList(
			torchRoomToHotRoom, hotRoomToTorchRoom, hotRoomToLadderRoom,
			ladderToHotRoom, intraversableLadderToShovelRoom,
			traversableLadderToShovelRoom, shovelToLadderRoom,
			invisibleHotToShovelRoom, invisibleShovelToHotRoom,
			invisibleTorchToShovelRoom, invisibleShovelToTorchRoom),
			"The fire monster has surrounded you with a wall of ice.\n" +
			"Unable to withstand the cold, you lose consciousness.",
			"You attack the monster with your firespear, and it melts " +
			"into a puddle.\nYou have defeated the monster!", 4);
		addMonster(iceMonster);

		// this exit is instantiated here because it requires references
		// to the relevant monster(s)
		torchToFireRoom = new DefeatMonsterExit(fireRoom,
			"a door covered by ice", Arrays.asList(iceMonster), true,
			"The ice on this door won't melt until the ice monster is " +
			"defeated.", "Now that the ice monster has been  defeated,\n" +
			"the ice has melted enough for you to open the door.\n" +
			"You open the door and go through.", true);
		torchRoom.addExit("icy door", torchToFireRoom);

		// set sword to react to monsters
		sword.addMonster(vineMonster);
		sword.addMonster(rockMonster);
		sword.addMonster(fireMonster);
		sword.addMonster(iceMonster);
			
	}

}

