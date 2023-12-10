import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import Blackjack.Static;

import java.util.Scanner;

import Menu.GameOver;
import Menu.Welcome;
import Objects.*;

public class Client {

	static String IP = "127.0.0.1";
	static int PORT = 4999;
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		String msg = "";		
		String [] parts = null;
		String command = "";
		String cargs = "";
		Scanner scnr = new Scanner(System.in);
		String s = "";
		while (true){
			msg = ReceiveMsg();
			parts = msg.split(":", 2);
			command = parts[0];
			cargs  = parts[1];

			// System.out.println("### msg: [" + msg + "]");
			// System.out.println("### command: [" + command + "]");
			// System.out.println("### cargs [: " + cargs + "]");

			if (command.equals("input")){
				s = Static.scannerObjectString();
				SendMsg(s);

				TimeUnit.MILLISECONDS.sleep(10);
			
			}
			else if (command.equals("menu")){
				switch (cargs) {
					case "welcome":
						Welcome.printWelcome();
						break;
					case "cls":
						Static.CLS();
					case "over":
						GameOver.printGameOver();
					default:
						break;
				}
			}
			else{
				System.out.println(cargs);
				TimeUnit.MILLISECONDS.sleep(10);
			}
			
			
		}

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
		
			TimeUnit.MICROSECONDS.sleep(10);
							
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

			TimeUnit.MICROSECONDS.sleep(10);
				
			// Close the ServerSocket
			socket.close();
		}catch(Exception e) {
			// Close the ServerSocket
			// serversocket.close();
		}
		return rcard;
	}

	public static String ReceiveMsg(){
		String message = "";
		try {
			Socket socket = new Socket("127.0.0.1",4999);
		
			// Set up input stream to read data from the client
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			// Read a message from the client
			message = bf.readLine();

			TimeUnit.MILLISECONDS.sleep(10);

			// Close the ServerSocket
			socket.close();

			return message;
		}catch(Exception e) {
			// Close the ServerSocket
			// serversocket.close();
		}
		return message;
	}
}
