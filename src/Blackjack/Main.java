

import Game.Game;

// import StaticFuntions.Static.*;

public class Main {
	public static Boolean verbose = false; // for debugging option
	// public static void main(String [] args) throws IOException, InterruptedException {
	public static void main(String [] args) {
		// Creating a Game
		Game game = new Game();
		game.rounds(); // Main loop of the game
	}
}
