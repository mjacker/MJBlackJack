package Blackjack;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import Objects.*;

public class Server {
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

	public static void SendMsg(ServerSocket serversocket, String msg){
		try{
			Socket socket = serversocket.accept();

			// Send the message to the server
			PrintWriter pr = new PrintWriter(socket.getOutputStream());

			// pr.println(stringcard);
			pr.println(msg);
			pr.flush();

			TimeUnit.SECONDS.sleep(1);
					
			// Close the ServerSocket
			socket.close();
		} catch (Exception e) {
			//
			
		}
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

    	public static String ReceiveMsg(ServerSocket serversocket){
        String message = "";
		try {
			// Accept a client connection
			Socket socket = serversocket.accept();
		
			// Set up input stream to read data from the client
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			// Read a message from the client
			message = bf.readLine();

			TimeUnit.SECONDS.sleep(1);
				
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			// socket.close();
		}
		return message;
	} 
}
