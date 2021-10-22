package game;

/**
 * Room.java
 * 
 * Class to model a room in the game.
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

import java.util.Map; // for maps
import java.util.HashMap; // for maps
import java.util.Collections; // for unmodifiable collections

public abstract class Room {

	/**
	 * A description of this room.
	 */
	private String description;

	/**
	 * A map of all exits specific to this room (maps exit names to exits).
	 */
	private Map<String, Exit> exits;

	/**
	 * A map of all commands specific to this room (maps commands to the
	 * objects that will execute those commands).
	 */
	private Map<String, Command> commands;

	/**
	 * A map of all items in the room (maps item names to Item objects).
	 */
	private Map<String, Item> items;

	/**
	 * The monster associated with this room.
	 */
	private Monster monster;

	/**
	 * Constructor.
	 * @param description A String describing this room to the user.
	 */
	public Room(String description) {
		this.description = description;
		items = new HashMap<String, Item>(10);
		exits = new HashMap<String, Exit>(10);
		commands = new HashMap<String, Command>(10);
		monster = null;
	}

	/**
	 * Retrieve a description of this room (to the user).
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of this room.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Add an item to the room, mapping it to the name of the item, unless
	 * a non-null item is already mapped to that key.
	 * If a non-null value is already mapped to this key, then the
	 * preexisting value will not be overwritten and the method will
	 * return this preexisting value. This means that all new items
	 * added to the room must have unique names to serve as keys.
	 * @param item The item to add to the room.
	 * @return Returns null if the item was successfully added to the room,
	 * otherwise returns the item currently mapped to the same name.
	 */
	public Item addItem(Item item) {
		return items.putIfAbsent(item.getName(), item);
	}

	/**
	 * Remove an item from the room by name.
	 * @param name The name of the item to remove.
	 * @return The removed item (null if the item was equal to null
	 * or if no mapping for the specified key was found)
	 */ 
	public Item removeItem(String name) {
		return items.remove(name);
	}

	/**
	 * Check if this room contains an item with the specified name.
	 * @param name The name of the item.
	 * @return Whether or not this room contains an item with the specified
	 * name.
	 */
	public boolean containsItem(String name) {
		return items.containsKey(name);
	}

	/**
	 * Retrieve the item with the specified name.
	 * @param name The name of the item.
	 * @return The item with the specified name (null if there is none).
	 */
	public Item getItem(String name) {
		return items.get(name);
	}

	/**
	 * Retrieve a read-only map of items in the room.
	 * @return A read-only map of items in the room.
	 */
	public Map<String, Item> getItems() {
		return Collections.unmodifiableMap(items);
	}

	/**
	 * Add an exit to the room, mapping it to the specified key, unless
	 * a non-null exit is already mapped to that key.
	 * If a non-null value is already mapped to this key, then the
	 * preexisting value will not be overwritten and the method will
	 * return this preexisting value. This means that all new exits
	 * added to the room must have unique names to serve as keys.
	 * @param name The name to which to map the exit.
	 * @param exit The exit to add to the room.
	 * @return Returns null if the key was successfully mapped to the
	 * parameter "exit", otherwise returns the exit currently mapped to
	 * the key.
	 */
	public Exit addExit(String name, Exit exit) {
		return exits.putIfAbsent(ParsingUtilities.formatText(name), exit);
	}

	/**
	 * Remove an exit from the room by name.
	 * @param name The name of the exit to remove.
	 * @return The removed exit (null if the exit was equal to null
	 * or if no mapping for the specified key was found)
	 */ 
	public Exit removeExit(String name) {
		return exits.remove(name);
	}

	/**
	 * Check if the room contains an exit by name.
	 * @param name The name of the exit.
	 * @return Whether or not the room contains the specified exit.
	 */
	public boolean containsExit(String name) {
		return exits.containsKey(name);
	}

	/**
	 * Retrieve the map of exits in the room.
	 * @return The map of exits in the room.
	 */
	public Map<String, Exit> getExits() {
		return Collections.unmodifiableMap(exits);
	}

	/**
	 * Check if this room currently contains a monster.
	 * @return Whether or not this room currently contains a monster.
	 */
	public boolean containsMonster() {
		return monster != null;
	}

	/**
	 * Add a monster to the room.
	 * PRECONDITION: containsMonster() returns false.
	 * POSTCONDITION: The specified monster has been added to the room.
	 * @param monster The monster to add to the room.
	 */
	public void addMonster(Monster monster) {
		this.monster = monster;
	}

	/**
	 * Retrieve the monster associated with this room (or null if none).
	 * @return The monster associated with this room (or null if none).
	 */
	public Monster getMonster() {
		return monster;
	}

	/**
	 * Remove the monster associated with this room.
	 * POSTCONDITION: The monster associated with this room has been removed.
	 */
	public void removeMonster() {
		this.monster = null;
	}

	/**
	 * Add an command to the room, mapping it to the specified key, unless
	 * a non-null command is already mapped to that key.
	 * If a non-null value is already mapped to this key, then the
	 * preexisting value will not be overwritten and the method will
	 * return this preexisting value. This means that all new commands
	 * added to the room must have unique keys.
	 * @param key The key to which to map the command.
	 * @param command The Command object to add.
	 * @return Returns null if the key was successfully mapped to the
	 * parameter "command", otherwise returns the command currently mapped
	 * to the key.
	 */
	public Command addCommand(String key, Command command) {
		return commands.putIfAbsent(ParsingUtilities.formatText(key), command);
	}

	/**
	 * Remove an command from the room by name.
	 * @param name The name of the command to remove.
	 * @return The removed Command object (null if the command was equal
	 * to null or if no mapping for the specified key was found)
	 */ 
	public Command removeCommand(String name) {
		return commands.remove(name);
	}

	/**
	 * Retrieve the map of commands in the room.
	 * @return The map of commands in the room.
	 */
	public Map<String, Command> getCommands() {
		return Collections.unmodifiableMap(commands);
	}

	/**
	 * If this room is the current room in the game, take the action 
	 * associated with it (if any).
	 * PRECONDITION: This room is the current room in the game.
	 */
	public abstract void act();
	
}
