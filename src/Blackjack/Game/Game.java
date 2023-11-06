package Game;
import java.util.ArrayList;

import Menu.GameMenu;
import Objects.Dealer;
import Objects.Deck;
import Objects.Player;

public class Game implements GameInterface{
	// Game Elements
	int delayTime = 500;
	int number_players;
	int number_deck;
	GameMenu menu = new GameMenu();

	// BlackJack Elements
    Dealer dealer = new Dealer();
    Deck deck;
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> leftplayers = new ArrayList<Player>();
    
	// Constructor
	/**
	 * Create the BlackJack Game
	 */
    public Game(){    
		// Cleaning screen, print menu
		Static.Funtions.CLS();	
		menu.title();
		menu.gameStarting();

		// Ask user custom parameters
		int number_players = numberOfPlayersToPlay();
		int number_deck = numberOfDecksToUse();

        // Initilize elements 
		this.players = createNplayers(number_players);
        this.deck = new Deck(number_deck);

		menu.enterToStard();	
    }	

	public int numberOfPlayersToPlay(){
		System.out.println("How many player will play in this game? [1-7]: ");
		int n = 0;
		try {
			n =  Integer.valueOf(Static.Funtions.scannerObjectString());
			while (!(n > 0 && n <= 7)){
				System.out.println("---> Number not in range [1-7]. Try again...");
				n =  Integer.valueOf(Static.Funtions.scannerObjectString());
			}
		}
		catch (Exception e) {
			System.out.println("Input not a number! Try again...\n\n");
			numberOfPlayersToPlay();
		}
		return n;
	}

	public int numberOfDecksToUse(){
		System.out.println("\nHow many decks will be use for this game? [1-8]: ");
		int n = 0;
		try {
			n =  Integer.valueOf(Static.Funtions.scannerObjectString());
			while (!(n > 0 && n <= 8)){
				System.out.println("---> Number not in range [1-8]. Try again...");
				n =  Integer.valueOf(Static.Funtions.scannerObjectString());
			}
		}
		catch (Exception e) {
			System.out.println("Input not a number! Try again...\n\n");
			numberOfDecksToUse();
		}
		return n;
	}
    
	/**
	 * Create an array of Players for the game
	 * @param nplayers Number of players to be created.
	 * @return return the array of players created with names and balance account.
	 */
	private ArrayList<Player> createNplayers(int nplayers){
		// Creating n players 
		String name;
		ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < nplayers; i++){
			System.out.print("Player" + (i + 1) + " name: ");
			name = Static.Funtions.scannerObjectString();
            players.add(new Player(name, 3));
        }
		return players;
	}

    // only one round for this hw1
	/**
	 * The round represent the main Loop where the interaction
	 * between Dealer, table and players occurs.
	 */
    public void rounds() {
    	int countround = 0;
    	while (countround >= 0 && !players.isEmpty()){

			// clean console screen
			Static.Funtions.CLS();

			// Print the title
			menu.title();

			payBet(this.players, this.leftplayers, false);

			// Display players who left the table
			tableLeftPlayers(this.leftplayers);

			menu.rounds(countround, this.deck.countCards());
			Static.Funtions.wait(delayTime);	
			
			// dealer give cards to each player
    		System.out.println("*** Dealing cards ***");
			for (Player player : players) { 		      
				dealer.cardToPlayer(player, this.deck);
				Static.Funtions.wait(delayTime);	
		   }

    		// dealer give card to dealer
    		dealer.cardToDealer(this.deck);
			Static.Funtions.wait(delayTime);	
   
			// give a space to second group of card given by Dealer
			System.out.println("");

			// dealer give cards to each player
			for (Player player : players) { 		      
				dealer.cardToPlayer(player, this.deck);
				Static.Funtions.wait(delayTime);	
		   }
    		
			// dealer give a HIDDEN card to dealer
    		dealer.cardToDealer(this.deck, true);
			
			// // Here could wait 3 second to next screen or, press enter to continue
			// Static.Funtions.wait(3000);	
			System.out.println("Press to continue...");
			Static.Funtions.scannerObjectString();
			
    		
			// Dealer interact with each player 
			for (Player player : players) { 		      
				// clear screen
				Static.Funtions.CLS();
				menu.title();
				// Table view
				tableView(this.dealer, this.players, true);
    			System.out.println("\n************************************************");		
				System.out.println("Player " + Static.Funtions.fixedLengthString(player.getName(), 10) + " turn:");
				System.out.println("Your cards are: " + player.getCards() + " [Score: " + player.score() + "]");
				// System.out.println("Press enter to reveal your cards.");
    			System.out.println("************************************************");		
				// Static.Funtions.scannerObjectString();

				// revealing player cards
				// System.out.println(player.getCards());

				// Dealer ask to current player (for homework2 there is only one player, for miniproject added nplayers
				while (!player.isBust() && dealer.askHitOrStand(player)) {
					System.out.println(player.getName() +" action is: " + player.getAction());

					// check message value
					// System.out.println(("Is this hand's player busted?: " + player.isBust()));
					// System.out.println(("Currend hand score: " + player.score()));

					// If player choose to hit, dealer give him another card
					if (player.isStand())
							dealer.cardToPlayer(player, this.deck);

					// Print message if got bust
					// System.out.println(("Is this hand's player busted after receiving a card?: " + player.isBust()));
					// System.out.println(("Currend hand score: " + player.score()));

					if (player.isBust()) System.out.println("\n===>>> You got Busted! <<<===");
					else System.out.println("\nYou continue playing in this round.");
				}
				// System.out.println(player.getName() + " action is: " + player.getAction()); // Resquired on homework originally.

        		
				// System.out.println(Static.Funtions.verbose ? player.getName() + " choosed " + player.getAction(): "" );
    
				System.out.println("\n\nPress enter to next player continue...");
				Static.Funtions.scannerObjectString();
			}

			// Reveal hidden card
			dealer.revealHiddenCard();

			// Second Table view checking revealing card
			tableView(this.dealer, this.players, true);

			// # check round score and winner.
			roundScores(this.dealer, this.players);

			// show updated players balances
			tableBalances(this.players);

			// Clear all hands
			this.dealer.dropAllCards();
			for (Player player : players) player.dropAllCards();
			// this.players.get(0).dropAllCards();

			//Restar
			// numeros de rondas para HW1 
    		countround++;

			System.out.println("\n\n>> Press enter to go to next round...");
			System.out.println("\n************************************************");		
			Static.Funtions.scannerObjectString();
    	}
		Static.Funtions.CLS();
		menu.title();
		System.out.println("no more players in the table");
		Static.Funtions.wait(3000);
		System.out.println(" ... so  sad...");
		Static.Funtions.wait(3000);
		menu.printGameOver();
		Static.Funtions.wait(3000);
    }


    public static void tableView(Dealer dealer, ArrayList<Player> players, boolean showPlayers) {
		GameMenu menu = new GameMenu();
		if (!showPlayers) menu.title();
    	System.out.println("\n************************************************");		
    	System.out.println("*** Table view***");
    	
		// Print Players hands
		if (showPlayers) {
			for (Player player : players) { 		      
				System.out.println("Player *" + Static.Funtions.fixedLengthString(player.getName(), 10) + "* has: " + player.getCards()
				+ " [Score: " + player.score()+"]"
				+ " [Balance: " + player.getBalance() + "]."
				+ " [Status: " + (player.isBust() ? "BUSTED!." : "- ") + "]."); 	
			}
   		 }

    	// Print dealers hand
    	System.out.println("          Dealer has: " + dealer.getCards() + dealer.countHiddenCards() + " [Score: "+dealer.score()+"].");
		// System.out.println("Dealer Hidden card: " + dealer.getHiddenCards().toString());
		System.out.println("\n************************************************");		
	}

    public static void roundScores(Dealer dealer, ArrayList<Player> players) {
		System.out.println("*** FINAL ROUND SCORES ***");
		// Print dealer Score
		// System.out.println(dealer.getName() + "'s score: " + dealer.score());
		
		// Show Dealer Score
		System.out.println("- " + dealer.getName() + " [Score: " + dealer.score() + "].");

		// Check each player score
		for (Player player : players){

			System.out.println("\n- " + player.getName() + " [Score: " + player.score() + "] ");
			if (player.isBust()){
				System.out.print("╚══> BUSTED, loosing -1 bet.\n");
				player.updateBalance(-1);
			}
			else
				if (player.score() > dealer.score()){
					System.out.print("╚══> WIN: getting 2 bets.\n");
					player.updateBalance(2);
				}
				else if (player.score() == dealer.score()){
					System.out.print("╚══> DRAW: Taking 1 bet back.\n ");
					player.updateBalance(1);
				}
				else{
					System.out.print("╚══> LOSS: loosing -1 bet.\n ");
					player.updateBalance(-1);
				}

		}
	}

	public static void tableBalances(ArrayList<Player> players){
    	System.out.println("\n************************************************");		
		System.out.println("*** PLAYERS BALANCES UPDATED ***");
		for (Player player : players){
			System.out.println("\n- " + player.getName() + " [Balance: " + player.getBalance() + "] ");
		}
	}

	/**
	 * Each players must pay a 1$ Bet to join to the round game.
	 * If player has not enought money, will be keep out of the table.
	 * @param players Active players that will be play this round
	 * @param leftPlayers Left players who wont play on this round
	 * @param recursive flag variable to prevent printing menu
	 * @return
	 */
	public static int payBet(ArrayList<Player> players, ArrayList<Player> leftPlayers, boolean recursive){
		Player movingPlayer = null;
		if (!recursive){
			 // Pay bet to join this round, by default players pays 1$.
			System.out.println("\n************************************************");		
			System.out.println("*** Paying $1 Bet to join this round ***\n");
		}	
		for (Player player : players){
			if (player.getBalance() > 1){
				player.updateBalance(-1);
				System.out.println("- " + player.getName() + " Bet $1.");
			}
			else {
				System.out.println(player.getName() + " left the table.");
				// System.out.println("Reason: Blance cero.");
				// System.out.println("el que pierde esta en index: " + players.indexOf(player));
				// leftPlayers.add(players.remove(players.indexOf(player)));
				
				// if (!players.isEmpty())
				movingPlayer = players.remove(players.indexOf(player));
				leftPlayers.add(movingPlayer);

				// System.out.println(leftPlayers);
				// System.out.println(players);
				payBet(players, leftPlayers, true);
				return 0;
			}
		}
		return 0;

	}

	public static void tableLeftPlayers(ArrayList<Player> leftPlayers){
		if (!leftPlayers.isEmpty()){
			System.out.println("\n************************************************");		
			System.out.println("*** LEFT PLAYERS ***");
			for (Player leftplayer : leftPlayers){
				System.out.println("\n- " + leftplayer.getName()) ;
			} 
		}
	}
}
