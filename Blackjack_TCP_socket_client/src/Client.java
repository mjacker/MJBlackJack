import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.Scanner;
import Objects.*;

public class Client {

	static String IP = "127.0.0.1";
	static int PORT = 4999;
	
	public static void main(String[] args) throws IOException, InterruptedException{
		

		ReceiveMsg();

		Scanner scnr = new Scanner(System.in);
        String s = scnr.nextLine();
		scnr.close();
		System.out.println("your name is:" + s);
		SendMsg(s);


		/* 
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
		*/
	}
		
	public static Card SendCard(Card scard){
		try {
			Socket socket = new Socket(IP, PORT);
		
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

	public static void SendMsg(String message){
		try {
			Socket socket = new Socket(IP, PORT);
		
			// Send the message to the server
			PrintWriter pr = new PrintWriter(socket.getOutputStream());
	
			// pr.println(stringcard);
			pr.println(message);
			pr.flush();
		
			TimeUnit.SECONDS.sleep(1);
							
			// Close the ServerSocket
			socket.close();
		
		}catch(Exception e) {
			// Close the ServerSocket
			// serversocket.close();
		}
	}

	public static Card Receivecard(){
		Card rcard = null;
		try {
			Socket socket = new Socket("127.0.0.1",4999);
		
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
			// serversocket.close();
		}
		return rcard;
	}

	public static void ReceiveMsg(){
		try {
			Socket socket = new Socket("127.0.0.1",4999);
		
			// Set up input stream to read data from the client
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			// Read a message from the client
			String message = bf.readLine();

			System.out.println(message);

			TimeUnit.SECONDS.sleep(1);
				
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			// serversocket.close();
		}
	}
}
