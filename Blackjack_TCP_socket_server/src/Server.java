import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Create a ServerSocket that listens on port 4999
		ServerSocket ss = new ServerSocket(4999);
		System.out.println("Wait for connection");
		try {
			// Accept a client connection
			Socket s = ss.accept();
			   
			System.out.println("Client connected");
			System.out.println("##########################################");
			System.out.println("Port: " + ss.getLocalPort());
			System.out.println("Receive Buffer size: " + ss.getReceiveBufferSize());
			System.out.println("Reuse Address: " + ss.getReuseAddress());
			System.out.println("Get Channel: " + ss.getChannel());
			System.out.println("Class: " + ss.getClass());
			System.out.println("Inet Address: " + ss.getInetAddress());
			System.out.println("Local Socket: " + ss.getLocalSocketAddress());
			System.out.println("##########################################");
		
			// Loop to exchange messages with the client 3 times
			for (int i=1; i<=3; i++ ) {
							// Set up input stream to read data from the client
				InputStreamReader in = new InputStreamReader(s.getInputStream());
				BufferedReader bf = new BufferedReader(in);

				// Read a message from the client
				String str = bf.readLine();
				System.out.println(str);
			
				// Prepare a message to send to the client
				String messagefromserver = "Server send " + i + ":" + "World!";
			
				System.out.println(messagefromserver);
			
				// Set up output stream to send data to the client
				PrintWriter pr = new PrintWriter(s.getOutputStream());
				pr.println(messagefromserver);
				pr.flush();

				// Pause for 1 second
				System.out.println("------------------------------");
				TimeUnit.SECONDS.sleep(1);
				
			}
			// Close the ServerSocket
			ss.close();
		}catch(Exception e) {
			// Close the ServerSocket
			ss.close();
		}
	}
}
