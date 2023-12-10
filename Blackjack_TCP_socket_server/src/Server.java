
import java.util.Scanner;
import java.io.*;
import Blackjack.*;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Create a ServerSocket that listens on port 4999
	
		Game game = new Game(1, 1);
		game.rounds();

	}

/* 
		Deck deck = new Deck(1);

		Card c1 = deck.popCard();
		Card c2 = deck.popCard();
		Card c3 = deck.popCard();

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		//SECONDS CHECKPOINT
		SendCard(serversocket,c1);
		SendCard(serversocket,c2);
		SendCard(serversocket,c3);

		System.out.println(Receivecard(serversocket).toString());
		System.out.println(Receivecard(serversocket).toString());
		System.out.println(Receivecard(serversocket).toString());

		serversocket.close();
	}
	*/

	

	public static void CLS() {
		try
		{
			final String os = System.getProperty("os.name");

			System.out.println(os);
			
			if (os.contains("Windows"))
			{
				// Runtime.getRuntime().exec("cls");
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (IOException | InterruptedException ex) {}
	}

	public static String scannerObjectString(/*Scanner scnr*/){
    	Scanner scnr = new Scanner(System.in);
        String s = scnr.nextLine();

		// this scanner should be closed and open when calling this method
		// but it is not working in that way.
		// scnr.close();
        return s;
    }
		public static void wait(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
	    catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
	    }
	}

	public static String fixedLengthString(String string, int length) {
 	   return String.format("%1$"+length+ "s", string);
	}	
}
