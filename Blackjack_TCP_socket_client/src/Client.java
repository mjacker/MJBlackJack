import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import Objects.*;

public class Client {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Connect to the server on localhost (127.0.0.1) and port 4999
		// Socket s = new Socket("140.118.245.205",4999);
		// Socket s = new Socket("140.118.218.159",4999);


		Card card = Receivecard();
		System.out.println("The receive card is: " + card.toString());
		card = Receivecard();
		System.out.println("The receive card is: " + card.toString());
		card = Receivecard();
		System.out.println("The receive card is: " + card.toString());

		Card c1 = new Card("5h");
		Card c2 = new Card("6h");
		Card c3 = new Card("7h");

		SendCard(c1);
		SendCard(c2);
		SendCard(c3);


	}
		
	public static Card SendCard(Card scard){
		try {
			Socket socket = new Socket("127.0.0.1",4999);
		
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

/* 
		// Prepare a message to send to the server
		//String message2send = "Hello";
		//int message2send = 7;
		String stringcard = card.toString();
		
		// Send the message to the server
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		// pr.println(stringcard);
		pr.println(stringcard);
		pr.flush();
		
		// System.out.println(messagefromclient);
		
		// Set up input stream to read data from the server
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		
		// Read a message from the server
		String str = bf.readLine();
		System.out.println(str);
		
		// Pause for 1 second
		System.out.println("------------------------------");
		TimeUnit.SECONDS.sleep(1);
		
		// Close the socket
		s.close();
		*/

		public static Card Receivecard(){
			Card rcard = null;
		// Create a ServerSocket that listens on port 4999

		// System.out.println("Wait for connection");
		try {
			Socket socket = new Socket("127.0.0.1",4999);
		
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
