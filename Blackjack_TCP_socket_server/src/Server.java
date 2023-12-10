import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import Objects.*;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		ServerSocket serversocket = new ServerSocket(4999);			
		
		Deck deck = new Deck(1);

		Card card = deck.popCard();

		System.out.println( card);

		card = Receivecard(serversocket);

		System.out.println("la carta que recivo es: " + card.toString());
		serversocket.close();
	}


	public static Card Receivecard(ServerSocket serversocket){
		// Create a ServerSocket that listens on port 4999
		System.out.println("Wait for connection");
		Card rcard = null;
		try {
			// Accept a client connection
			Socket socket = serversocket.accept();
			   
			// System.out.println("Client connected");
			// System.out.println("##########################################");
			// System.out.println("Port: " + serversocket.getLocalPort());
			// System.out.println("Receive Buffer size: " + serversocket.getReceiveBufferSize());
			// System.out.println("Reuse Address: " + serversocket.getReuseAddress());
			// System.out.println("Get Channel: " + serversocket.getChannel());
			// System.out.println("Class: " + serversocket.getClass());
			// System.out.println("Inet Address: " + serversocket.getInetAddress());
			// System.out.println("Local Socket: " + serversocket.getLocalSocketAddress());
			// System.out.println("##########################################");
		
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
