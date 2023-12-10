package Blackjack;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Menu.Welcome;
import Objects.Dealer;
import Objects.Deck;
import Objects.Player;



public class Game {
	ServerSocket serversocket = new ServerSocket(4999);
    Dealer dealer = new Dealer(this.serversocket);
    // Declare the deck card
    Deck deck;
    
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> leftplayers = new ArrayList<Player>();
    
    //Centinel Variable
    Boolean check = true;
    
	int delayTime = 500;

	// ServerSocket serverSocket = null;

    
	// Constructor
    public Game(int number_players, int number_deck) throws IOException, InterruptedException{ 
		
		int DEFAULT_BALANCE = 3;

		Static.CLS();	
		Server.PrintAndSendMsg(serversocket, "Waiting to player to connect...");
		Server.SendMsg(this.serversocket, "menu:cls");
		Server.SendMsg(this.serversocket, "menu:welcome");
		Welcome.printWelcome();
		// press Enter to start the game
    	Server.PrintAndSendMsg(serversocket, "\n************************************************");		
        Server.PrintAndSendMsg(serversocket, "*** The game is starting...***");
    	Server.PrintAndSendMsg(serversocket, "\n************************************************");		
		Server.PrintAndSendMsg(serversocket, "***********************************************");
		Server.PrintAndSendMsg(serversocket, "*** The game is starting...**");
		Server.PrintAndSendMsg(serversocket, "***********************************************");

		// ! here need a 1-3 player error handler
		// Server.PrintAndSendMsg(serversocket, "How many player will play [1-3]: ");
		
		while (check) {
		    try {
		        // number_players = Integer.valueOf(Static.scannerObjectString());
		        number_players = 1;
		        // If the user entered a number, the code will reach here
		        check = false; // Exit the loop since a valid number is entered
		    } catch (NumberFormatException e) {
		        // If the user did not enter a number, the code will reach here
		        Server.PrintAndSendMsg(serversocket, "Please enter a valid number.");
		        // Optionally, you can add more instructions or prompt the user again
		        check = true;
		    }
		}


		

		// System.out.println(number_players);

    	// Creating n players 
		String name;
        for (int i = 0; i < number_players; i++){
			Server.PrintAndSendMsg(serversocket, "Waiting for player name: ");
			System.out.print("Player" + (i + 1) + " name: ");
			
			Server.PrintAndSendMsg(serversocket, " Please enter your name");
			Server.SendMsg(this.serversocket, "input:");
			name = Server.ReceiveMsg(serversocket);

			// name = Static.scannerObjectString();


			// name = scannerObjectString();
            this.players.add(new Player(name, DEFAULT_BALANCE));
        }
        // Initilize the number of Decks
        deck = new Deck(number_deck);

		// Server.PrintAndSendMsg(serversocket, "\nPress enter to start.");
		// Static.scannerObjectString();

			rounds(serversocket);
    }	


    // only one round for this hw1
    public int rounds(ServerSocket serverSocket) {
    	int countround = 0;
		String tmpmsg = "";
    	while (countround >= 0 && !players.isEmpty()){

			Static.CLS();
			Welcome.printWelcome();

			// Pay bet to join this round.
			Server.PrintAndSendMsg(serversocket, "\n************************************************");		
			Server.PrintAndSendMsg(serversocket, "*** To play in this round need to pay $1 or $2 bet.***");
			Server.PrintAndSendMsg(serversocket, "***********************************************");
			Server.PrintAndSendMsg(serversocket, "*** To play in this round need to pay $1 or $2 bet.***");


			// Server.PrintAndSendMsg(serversocket, "*** Paying $1 Bet to join this round ***");
			payBet(this.players, this.leftplayers, this.serversocket);

			// players who left the table
			tableLeftPlayers(this.leftplayers, serverSocket);

			// Check is there are players in the table
			if(players.isEmpty()){
				
				Server.PrintAndSendMsg(serversocket, "no more players in the table");
				Server.PrintAndSendMsg(serversocket, "Press enter to exit...");
				Static.scannerObjectString();
				break;
			}

			tmpmsg = "\n************************************************";
			System.out.println(tmpmsg); Server.SendMsg(serversocket, "print:" + tmpmsg);
			Server.PrintAndSendMsg(serversocket, "*** Round number: " + (countround + 1) + " ***");
			Server.PrintAndSendMsg(serversocket, "*** cards left in deck: " + this.deck.countCards() + " ***");
			Server.PrintAndSendMsg(serversocket, "************************************************");		
    		Server.PrintAndSendMsg(serversocket, "*** Dealing cards ***");

			Static.wait(delayTime);	
			// dealer give cards to each player
			for (Player player : players) { 		      
				dealer.cardToPlayer(player, this.deck);
				Static.wait(delayTime);	
		   }

    		// dealer give card to dealer
    		dealer.cardToDealer(this.deck);
			Static.wait(delayTime);	
   
			// give a space to second group of card given by Dealer
			Server.PrintAndSendMsg(serversocket, "");

			// dealer give cards to each player
			for (Player player : players) { 		      
				dealer.cardToPlayer(player, this.deck);
				Static.wait(delayTime);	
		   }
    		
			// dealer give a HIDDEN card to dealer
    		dealer.cardToDealer(this.deck, true);
			
			// // Here could wait 3 second to next screen or, press enter to continue
			// Static.wait(3000);	
			Server.PrintAndSendMsg(this.serversocket, "Press to continue...");
			// Static.scannerObjectString();
			
    		
			// Dealer interact with each player 
			for (Player player : players) { 		      
				// clear screen
				Static.CLS();
				Menu.Welcome.printWelcome();
				// Table view
				tableView(this.dealer, this.players, true, serverSocket);
    			Server.PrintAndSendMsg(serversocket, "\n************************************************");		
				Server.PrintAndSendMsg(serversocket, "Player " + Static.fixedLengthString(player.getName(), 10) + " turn:");
				Server.PrintAndSendMsg(serversocket, "Your cards are: " + player.getCards() + " [Score: " + player.score() + "]");
				// Server.PrintAndSendMsg(serversocket, "Press enter to reveal your cards.");
    			Server.PrintAndSendMsg(serversocket, "************************************************");		
				// Static.scannerObjectString();

				// revealing player cards
				// System.out.println(player.getCards());

				// Dealer ask to current player (for homework2 there is only one player, for miniproject added nplayers
				while (!player.isBust() && dealer.askHitOrStand(this.serversocket, player)) {
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

					if (player.isBust()) Server.PrintAndSendMsg(serversocket, "\n>>> " + player.getName() + " got Busted!");
					else Server.PrintAndSendMsg(serversocket, "\n>>> " + player.getName() + " continue playing in this round.");
					
					// Server.PrintAndSendMsg(serversocket, "***Choose you action to continue the game***\n"
							// + "			*** [continue, end]    ***");
					
				}
				// System.out.println(player.getName() + " action is: " + player.getAction()); // Resquired on homework originally.

        		
				System.out.println(Static.verbose ? player.getName() + " choosed " + player.getAction(): "" );
    
				Server.PrintAndSendMsg(serversocket, "\n\nPress enter to next player continue...");
				Static.scannerObjectString();
			}

			// Reveal hidden card
			dealer.revealHiddenCard();

			// Second Table view checking revealing card
			tableView(this.dealer, this.players, true, serversocket);

			// # check round score and winner.
			roundScores(this.dealer, this.players, serverSocket);

			// show updated players balances
			tableBalances(this.players, serverSocket);

			// Clear all hands
			this.dealer.dropAllCards();
			for (Player player : players) player.dropAllCards();
			// this.players.get(0).dropAllCards();

			//Restar
			// numeros de rondas para HW1 
    		countround++;

			Server.PrintAndSendMsg(serversocket, "\n\n>> Press enter to go to next round...");
			Server.PrintAndSendMsg(serversocket, "\n************************************************");		
			Server.SendMsg(this.serversocket, "input:");

			// Static.scannerObjectString();
    	}
		Static.CLS();
		Menu.Welcome.printWelcome();
		Static.wait(3000);
		Server.PrintAndSendMsg(serversocket, " ... so  sad...");
		Server.PrintAndSendMsg(serversocket, "\n\n");
		Static.wait(3000);
		Menu.GameOver.printGameOver();
		Server.SendMsg(this.serversocket, "menu:cls");
		Static.wait(3000);
		return 0;
    }
    
    public void tableView(Dealer dealer, ArrayList<Player> players, boolean showPlayers, ServerSocket serverSocket) {
		if (!showPlayers) Welcome.printWelcome();
    	Server.PrintAndSendMsg(serversocket, "\n************************************************");		
    	Server.PrintAndSendMsg(serversocket, "*** Table view***");
    	
		// Print Players hands
		if (showPlayers) {
			for (Player player : players) { 		      
				Server.PrintAndSendMsg(serversocket, "Player *" + Static.fixedLengthString(player.getName(), 10) + "* has: " + player.getCards()
				+ " [Score: " + Static.fixedLengthString(Integer.toString(player.score()), 2) +"]"
				+ " [Balance: " + player.getBalance() + "]"
				+ " [Bet: " + Integer.toString(player.getBet()) + "]"
				+ " [Status: " + (player.isBust() ? "BUSTED!" : "- ") + "]."); 	
			}
   		 }

    	// Print dealers hand
    	Server.PrintAndSendMsg(serversocket, "          Dealer has: " + dealer.getCards() + dealer.countHiddenCards() + " [Score: "+dealer.score()+"].");
		// Server.PrintAndSendMsg(serversocket, "Dealer Hidden card: " + dealer.getHiddenCards().toString());
		Server.PrintAndSendMsg(serversocket, "\n************************************************");		
	}

    public void roundScores(Dealer dealer, ArrayList<Player> players, ServerSocket serverSocket) {
		Server.PrintAndSendMsg(serversocket, "*** FINAL ROUND SCORES ***");
		// Print dealer Score
		// System.out.println(dealer.getName() + "'s score: " + dealer.score());
		
		// Show Dealer Score
		Server.PrintAndSendMsg(serversocket, "- " + dealer.getName() + " [Score: " + dealer.score() + "].");

		// Check each player score
		// Busted and Loss is zero because we already substract one coin in the 
		// beginning of the round
		for (Player player : players){

			Server.PrintAndSendMsg(serversocket, "\n- " + player.getName() + " [Score: " + player.score() + "] ");
			if (player.isBust()){
				System.out.print("╚══> BUSTED, loosing -" + Integer.toString(player.getBet()) + " bet.\n");
				player.updateBalance(0);
			}
			else
				if (player.score() > dealer.score()){
					System.out.print("╚══> WIN: getting " + Integer.toString(player.getBet() * 2) + " bets.\n");
					player.updateBalance(player.getBet() * 2);
				}
				else if (player.score() == dealer.score()){
					System.out.print("╚══> DRAW: Taking " + Integer.toString(player.getBet()) + " bet back.\n ");
					player.updateBalance(player.getBet());
				}
				else{
					System.out.print("╚══> LOSS: loosing -" + Integer.toString(player.getBet()) + " bet.\n ");
					player.updateBalance(0);
				}

		}
	}

	public void tableBalances(ArrayList<Player> players, ServerSocket serverSocket){
    	Server.PrintAndSendMsg(serversocket, "\n************************************************");		
		Server.PrintAndSendMsg(serversocket, "*** PLAYERS BALANCES UPDATED ***");
		for (Player player : players){
			Server.PrintAndSendMsg(serversocket, "\n- " + player.getName() + " [Balance: " + player.getBalance() + "] ");
		}
	}

	public static int payBet(ArrayList<Player> players, ArrayList<Player> leftPlayers, ServerSocket serversocket){
		Player movingPlayer = null;
		boolean error = false;
		int oneBet = 0;
		String tmpmsg = "";


		for (Player player : players){
			// Asking how much bet do the player wants.
			do {
				try {
					oneBet = -1;
					do {
						tmpmsg = "### ==> " + player.getName() + " your balance is: " + player.getBalance();
						System.out.println(tmpmsg);
						Server.SendMsg(serversocket, "print:" + tmpmsg);

						// Server.PrintAndSendMsg(serversocket, "Please input your bet [1, 2] (Enter 0 to exit.): ");
						// Server.SendMsg(serversocket, "print:Please input your bet [1, 2] (Enter 0 to exit.): ");
						
						// oneBet = Integer.parseInt(Static.scannerObjectString());
						Server.SendMsg(serversocket, "print: Pease input your bet [1,2] (Eneter 0 to exit.)");
						Server.SendMsg(serversocket, "input:");
						oneBet = Integer.parseInt(Server.ReceiveMsg(serversocket));	

						error = false;
						if (oneBet == 0){
							Server.PrintAndSendMsg(serversocket, "--> " + player.getName() + " is leaving the table.");
							// break; // disabled, making wrong leaving for next players.
							player.setBet(0);
							
						}
						else if (!(oneBet >= 1 && oneBet <= 2)){
							Server.PrintAndSendMsg(serversocket, "\n\n>>> You only can place one bet in this range! [1, 2]");
							Server.PrintAndSendMsg(serversocket, "Please try again...");
							Server.PrintAndSendMsg(serversocket, "");
						}
						else{
							player.setBet(oneBet);
							// Server.PrintAndSendMsg(serversocket, "Player " + player.getName() + "placed one bet: $" + player.getBet());
						}
					}
					while (!(oneBet >= 0 && oneBet <= 2));
						
				}
				catch (Exception e){
					Server.PrintAndSendMsg(serversocket, "\n\n>>> Not a valid number!, please try again.");
					error = true;
				}
			}
			while (error);
		}
		tmpmsg  = "-------------------------------------------------------------\n";
		System.out.println(tmpmsg);
		Server.SendMsg(serversocket, "print:" + tmpmsg);


		for (Player player : players) {
			//payment to enter the round
			if ((player.getBalance() - player.getBet()) >= 0 && player.getBet() > 0){
				player.updateBalance(-player.getBet());
				tmpmsg = "- " + player.getName() + " placed one bet of value: " + Integer.toString(player.getBet());
				System.out.println(tmpmsg); Server.SendMsg(serversocket, "print:" + tmpmsg);
			}
			else {
				System.out.println(player.getName() + " left the table.");
				// Server.PrintAndSendMsg(serversocket, "Reason: Blance cero.");
				// Server.PrintAndSendMsg(serversocket, "el que pierde esta en index: " + players.indexOf(player));
				// leftPlayers.add(players.remove(players.indexOf(player)));
				
				// if (!players.isEmpty())
				movingPlayer = players.remove(players.indexOf(player));
				leftPlayers.add(movingPlayer);

				// System.out.println(leftPlayers);
				// System.out.println(players);
				// payBet(players, leftPlayers);
				return 0;
			}
		}
		return 0;

	}

	public static void tableLeftPlayers(ArrayList<Player> leftPlayers, ServerSocket serversocket){
		if (!leftPlayers.isEmpty()){
			Server.PrintAndSendMsg(serversocket, "\n************************************************");		
			Server.PrintAndSendMsg(serversocket, "*** LEFT PLAYERS ***");
			for (Player leftplayer : leftPlayers){
				Server.PrintAndSendMsg(serversocket, "\n- " + leftplayer.getName()) ;
			} 
		}
	}
}
