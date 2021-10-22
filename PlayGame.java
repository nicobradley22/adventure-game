package game;

/**
 * PlayGame.java
 * 
 * Program to control the playing of this game.
 * Serves as the Controller in the Model-View-Controller pattern.
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

public class PlayGame {
    
    public static void main(String[] args) {
		// reference to the Game object
        Game game = Game.getInstance();
		// reference to the Parser object
        Parser parser = new Parser(game.getInventory());

		System.out.println("-----------------------------------------");
        System.out.println("Welcome to the game.");
		game.printIntro();

        while (! game.isOver()) {
			game.incrementTurns();
			game.activate();
            parser.executeTurn();
		}

        System.out.println("Game over.");
		System.out.println("-----------------------------------------");
    }
    
}
