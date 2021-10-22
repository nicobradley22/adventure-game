package game;

/**
 * Game.java
 * 
 * Class to model the game.
 * Serves as the Model in the Model-View-Controller pattern.
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 *
 * @author Nico Bradley, Carrie Snodgrass, and Claire Wagner
 * Wheaton College, CSCI 245, Spring 2021
 * Project 4
 * 18 Feb. to 19 March 2021
 */

import java.util.List; // for lists
import java.util.ArrayList; // for lists
import java.util.Arrays; // for lists
import java.util.Map; // for maps
import java.util.HashMap; // for maps
import java.util.Collection; // for collections
import java.util.Collections; // for unmodifiable collections

public class Game {
	
	/**
	 * Static initializer to call the constructor.
	 */
	static {
		game = new Game();
	}

	/**
	 * Instance of this class to share with other classes.
	 */
	private static Game game;

	/**
	 * Retrieve a reference to the instance of this class.
	 * @return The instance of this class.
	 */
	public static Game getInstance() {
		return game;
	}

    /**
     * The current room the user is in. This serves to
     * purposes-- it is our only permanent connection to
     * the rooms in this game (the other rooms are reachable
     * by traversing this room's "doors"-- and it maintains
     * the state by representing the user's current location.
     */
    private Room currentRoom;

	/**
	 * A list of all of the areas in the game.
	 */
	private List<GameArea> areas;

	/**
	 * Map that maps global commands to Command objects that will execute those
	 * commands.
	 */
	private Map<String, Command> globalCommands;

    /**
     * Keeps track of whether this game is over or not.
     */
    private boolean over;

	/**
	 * Keeps track of the number of turns that the player has taken so far.
	 */
	private int turns;
    
	/**
	 * Inventory object to allow interaction with the inventory.
	 */
	private Inventory inventory;

    /**
     * Constructor to set up the game (private to implement Singleton pattern).
     */
    private Game() {
		inventory = new Inventory(10);
		populateGlobalCommands();
        over = false;
		turns = 0;
		
		// the first room of the game, which connects all the areas together
		Room startRoom = new GenericRoom(
			"You are at the landing site of your spaceship.\n" +
			"To the south is your spaceship.\n" +
			"To the west is a path leading into a forest.\n" +
			"To the north is a snowy mountain.\n" +
			"To the east is a sandy beach.");
		
		// item references to share between areas
		Item spaceshipPart1 = new GenericItem("unobtanium fuel",
			"unobtanium fuel to power your spaceship", true);
		Item spaceshipPart2 = new GenericItem("unobtanium alloy",
			"unobtanium metal to fix your spaceship", true);
		Item spaceshipPart3 = new GenericItem("unobtanium gas",
			"unobtanium gas for your air filtration system", true);
		Item[] spaceshipParts =
			{spaceshipPart1, spaceshipPart2, spaceshipPart3};
		Item scubasuit = new GenericItem("scubasuit", "a scubasuit that " +
				"allows you to travel underwater", true);
		Item wintercoat = new GenericItem("winter coat",
			"a winter coat that protects you from the cold", true);

		// initialize the areas of the game
		areas = new ArrayList<GameArea>(4);
		areas.add(new StartArea(startRoom, spaceshipParts, inventory));
		areas.add(new ForestArea(startRoom, spaceshipPart1, wintercoat,
			inventory));
		areas.add(new SnowArea(startRoom, spaceshipPart2, scubasuit, wintercoat,
			inventory));
		areas.add(new WaterArea(startRoom, spaceshipPart3, scubasuit,
			inventory));

		// set the starting room of the game
		setCurrentRoom(startRoom);
    }
    
	/**
	 * Display the introductory text of the game.
	 */
	public void printIntro() {
		System.out.println("You have crash-landed on a mysterious planet, " +
			"and your spaceship is too damaged\nto take off again. " +
			"This planet contains the ruins of an ancient civilization.\n" 
			+ "You've heard rumours of a magical substance called unobtanium "
			+ "that can fix\nanything. Right now, you need to find fuel to "
			+ "power your ship, metal to fix\nthe body of your ship, and gas "
			+ "to fix your air filtration system.\n"
			+ "Maybe this planet has some unobtanium lying around...");
		System.out.println("You walk out of your spaceship and look around.");
		System.out.println("-----------------------------------------");
		System.out.println(currentRoom.getDescription());
	}

    /**
     * Is this game over or not?
	 * @return Whether or not this game is over.
     */
    public boolean isOver() {
		return over;
	}

	/**
     * Return the room in which the user is currently.
	 * @return The room in which the user is currently.
     */
    public Room getCurrentRoom() {
		return currentRoom;
	}
    
	/**
     * Move into a different current room.
	 * @param currentRoom The room into which to move.
     */
    public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * Retrieve a reference to the player's inventory.
	 * @return The player's inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Retrieve the number of turns that the player has taken so far.
	 * @return The number of turns that the player has taken so far.
	 */
	public int getTurns() {
		return turns;
	}

	/**
	 * Increment the number of turns that the player has taken so far by one.
	 */
	public void incrementTurns() {
		turns++;
	}

	/**
	 * Remove a monster from the game.
	 * @param monster The monster to remove.
	 */
	public void removeMonster(Monster monster) {
		for (GameArea area : areas) {
			if (area.containsMonster(monster)) {
				area.removeMonster(monster);
			}
		}
	}

   	/**
	 * Create and populate the global commands hash map.
	 */
	private void populateGlobalCommands() {
		globalCommands = new HashMap<String, Command>(10);
		addGlobalCommand("quit", new GameOver("You have quit the game."));
		addGlobalCommand("look", new Look());
		addGlobalCommand("help", new Help());
		addGlobalCommand("inventory", new CheckInventory(inventory));
		Command get = new Get(inventory);
		addGlobalCommand("get", get);
		addGlobalCommand("pick up", get);
		Command drop = new Drop(inventory);
		addGlobalCommand("drop", drop);
		addGlobalCommand("put down", drop);
		addGlobalCommand("use", new Use(inventory));
		addGlobalCommand("examine", new Examine(inventory));
		addGlobalCommand("travel", new Travel());
		addGlobalCommand("hints", new Hints());
	}

	/**
	 * Add a command to the map of global commands, mapping it to the
	 * given key, unless a non-null command is already mapped to that key.
	 * If a non-null value is already mapped to this key, then the
	 * preexisting value will not be overwritten and the method will
	 * return this preexisting value.
	 * @param key The string to which to map the command.
	 * @param command The command to map to the specified key.
	 * @return Returns null if the key was successfully mapped to the
	 * parameter "command", otherwise returns the command currently mapped
	 * to the key.
	 */
	public Command addGlobalCommand(String key, Command command) {
		return globalCommands.putIfAbsent(ParsingUtilities.formatText(key),
			command);
	}

	/**
	 * Retrieve a read-only map of all global commands.
	 * @return A read-only map of all global commands.
	 */
	public Map<String, Command> getGlobalCommands() {
		return Collections.unmodifiableMap(globalCommands);
	}

	/**
	 * Retrieve a collection of hints about the game.
	 * @return A collection of hints about the game.
	 */
	public Collection<String> getHints() {
		Collection<String> hints = Arrays.asList(
			"If you're not sure what to do, try typing \"help\" or \"look\".",
			"Examining different things in the game can give you helpful " +
				"clues about\n  how to interact with them.",
			"Type \"examine area\" to examine the entire area you're in.",
			"To travel through an exit, type \"travel\", or simply " +
			"type the name of the exit.",
			"When faced with a hostile creature, try using an item.",
			"Type \"quit\" to quit the game (you will lose any progress " +
				"you've made).");
		return hints;
	}

	/**
	 * Call the act() methods of all appropriate objects.
	 */
	public void activate() {
		currentRoom.act();
		for (Exit exit : currentRoom.getExits().values()) {
			exit.act();
		}
		for (GameArea area : areas) {
			for (Monster monster : area.getMonsters()) {
				monster.act();
			}
		}
		// make a temporary copy of all the items in the inventory
		// and then call the act method of each item in that copy
		// (to avoid ConcurrentModificationException)
		Collection<Item> items = new ArrayList<>(inventory.getItems().size());
		items.addAll(inventory.getItems().values());
		for (Item item : items) {
			item.act();
		}
	}

	/**
     * Indicate that the game is now over.
     */
    public void finishGame() {
		over = true;
	}
    
}
