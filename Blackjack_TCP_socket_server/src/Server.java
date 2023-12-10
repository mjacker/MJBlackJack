import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import Objects.*;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Create a ServerSocket that listens on port 4999
		ServerSocket serversocket = new ServerSocket(4999);

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
	public static Card SendCard(ServerSocket serversocket, Card scard){
		// Create a ServerSocket that listens on port 4999
		System.out.println("Wait for connection");
		
		try {
			// Accept a client connection
			Socket socket = serversocket.accept();

			// Send the message to the server
			PrintWriter pr = new PrintWriter(socket.getOutputStream());

			// pr.println(stringcard);
			pr.println(scard.toString());
			pr.flush();

			TimeUnit.SECONDS.sleep(1);
					
			// Close the ServerSocket
			socket.close();

		}catch(Exception e) {
			// Close the ServerSocket
			// socket.close();
		}

		return scard;
	}

	public static Card Receivecard(ServerSocket serversocket){
		System.out.println("Wait for connection");
		Card rcard = null;
		try {
			// Accept a client connection
			Socket socket = serversocket.accept();
		
			// Set up input stream to read data from the client
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			// Read a message from the client
			rcard = new Card(bf.readLine());

			TimeUnit.SECONDS.sleep(1);
				
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			// socket.close();
		}
		return rcard;
	}
}
