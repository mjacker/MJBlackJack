package Objects;

import java.net.ServerSocket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Blackjack.Server;
import Blackjack.Static;

public class Dealer extends Hand {

	ServerSocket serversocket = null;
	private String name = "";
	private ArrayList<Card> hiddencards = new ArrayList<Card>();

	public Dealer(ServerSocket serverSocket) {
		this.name = "DEALER";
		this.serversocket = serverSocket;
		
	}

	public String getName(){
		return this.name;
	}
	
	public void cardToPlayer(Player player, Deck deck) {
		Card c = deck.popCard();
		player.add(c);
		Server.PrintAndSendMsg(this.serversocket, "Card dealt to *" + Static.fixedLengthString(player.getName(), 10) + "   *\t is : " + c.toString() + " [Score: "+c.getId()+"]" + " [Balance: " + player.getBalance() + "] " + "[Bet: " + Static.fixedLengthString(Integer.toString(player.getBet()), 3) + "].");
	}
	
	public void cardToDealer(Deck deck) {
		Card c = deck.popCard();
		this.add(c);
		//this.add(new Card("Ah"));
		Server.PrintAndSendMsg(this.serversocket, "Card dealt to *" + Static.fixedLengthString(this.name, 10) + "   *\t is : " + c.toString() + " [Score: "+ this.score() +"].");
	}
	
	// overload function to handle hidden cards
	public void cardToDealer(Deck deck, boolean hidden) {
		Card c = deck.popCard();
		this.addHidden(c);
		//this.add(new Card("Ah"));
		Server.PrintAndSendMsg(this.serversocket, "Card dealt to *" + Static.fixedLengthString(this.name, 10) + "   *\t is : " + "Xx" + " [Score: "+ "X" +"].\n");
	}

	public void addHidden (Card card) {
		this.hiddencards.add(card);
	}

	public void revealHiddenCard(){
		Server.SendMsg(this.serversocket, "menu:cls");
		Server.SendMsg(this.serversocket, "menu:welcome");
		Static.CLS();
		Menu.Welcome.printWelcome();
		Card c = this.hiddencards.remove(0);
		Server.PrintAndSendMsg(this.serversocket, "************************************************");
		Server.PrintAndSendMsg(this.serversocket, "Show " + this.name + "'s second card is: " + c.toString() + " [Score: " + c.getId() + "]");
		Server.PrintAndSendMsg(this.serversocket, "************************************************");
		this.add(c);
	}

	public ArrayList<Card> getHiddenCards(){
		return this.hiddencards;
	}

	public String countHiddenCards(){
		if (this.hiddencards.size() > 0)
			return " [Xx]";
		else
			return "";
	}

	public boolean askHitOrStand(ServerSocket serverSocket, Player player) {
		String action = "";
		Server.PrintAndSendMsg(this.serversocket, "");
		Server.PrintAndSendMsg(this.serversocket, ">>> Choose your action <<<");
		Server.PrintAndSendMsg(this.serversocket, ">>>    [hit, stand]    <<<");
		
		// // Disabled manual input for testing.
		// // auto choosing is 50% hit, 50% stand
		// String [] playerchoises = {"hit", "stand"}; // for automatization
		// Static.wait(1000);	
		// player.setAction(playerchoises[new Random().nextInt(playerchoises.length)]);
		
		// Enabled manual input for hit and stand.
		Server.SendMsg(serverSocket, "input:");
		action = Server.ReceiveMsg(serversocket);
		player.setAction(action);
		
		return player.isStand();	
	}


}
