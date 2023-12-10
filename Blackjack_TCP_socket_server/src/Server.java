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
		// FIRST CHECKPOINT
		// card = Receivecard(serversocket);
		// System.out.println("la carta que recivo es: " + card.toString());
		
		//SECONDS CHECKPOINT
		SendCard(serversocket,c1);
		SendCard(serversocket,c2);
		SendCard(serversocket,c3);

		System.out.println(Receivecard(serversocket).toString());
		System.out.println(Receivecard(serversocket).toString());
		System.out.println(Receivecard(serversocket).toString());

		serversocket.close();


		/* 
		System.out.println("Wait for connection");
		try {
			// Accept a client connection
			Socket socket = serversocket.accept();
			   
			System.out.println("Client connected");
			System.out.println("##########################################");
			System.out.println("Port: " + serversocket.getLocalPort());
			System.out.println("Receive Buffer size: " + serversocket.getReceiveBufferSize());
			System.out.println("Reuse Address: " + serversocket.getReuseAddress());
			System.out.println("Get Channel: " + serversocket.getChannel());
			System.out.println("Class: " + serversocket.getClass());
			System.out.println("Inet Address: " + serversocket.getInetAddress());
			System.out.println("Local Socket: " + serversocket.getLocalSocketAddress());
			System.out.println("##########################################");
		
			// Loop to exchange messages with the client 3 times
			for (int i=1; i<=2; i++ ) {
							// Set up input stream to read data from the client
				InputStreamReader in = new InputStreamReader(socket.getInputStream());
				BufferedReader bf = new BufferedReader(in);

				// Read a message from the client
				String str = bf.readLine();
				System.out.println(str);
			
				// Prepare a message to send to the client
				String messagefromserver = "Server send " + i + ":" + "World!";
			
				System.out.println(messagefromserver);
			
				// Set up output stream to send data to the client
				PrintWriter pr = new PrintWriter(socket.getOutputStream());
				pr.println(messagefromserver);
				pr.flush();

				// Pause for 1 second
				System.out.println("------------------------------");
				TimeUnit.SECONDS.sleep(1);
				
			}
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			serversocket.close();
		
		}
		*/
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
			// serversocket.close();
		}

		return scard;
	}

	public static Card Receivecard(ServerSocket serversocket){
		// Create a ServerSocket that listens on port 4999
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
			// System.out.println(receivecard.toString());
		
			// Prepare a message to send to the client
			// String messagefromserver = "chau";
			// System.out.println(messagefromserver);
		
			// Set up output stream to send data to the client
			// PrintWriter pr = new PrintWriter(socket.getOutputStream());
			// pr.println(messagefromserver);
			// pr.flush();

			// Pause for 1 second
			// System.out.println("------------------------------");
			TimeUnit.SECONDS.sleep(1);
				
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			// serversocket.close();
		
		}

		return rcard;
	}

}
