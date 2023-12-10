import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import Objects.*;

public class Client {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Connect to the server on localhost (127.0.0.1) and port 4999
		Socket s = new Socket("127.0.0.1",4999);
		// Socket s = new Socket("140.118.245.205",4999);
		// Socket s = new Socket("140.118.218.159",4999);


		Card card = new Card("2h");

		// System.out.println(card.toString());


		// Loop to exchange messages with the server 15 times
			
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
	}

		public static Card Receivecard(Socket socket){
		// Create a ServerSocket that listens on port 4999
		System.out.println("Wait for connection");
		Card rcard = null;
		try {
		
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
