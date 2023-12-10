import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Client {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// Connect to the server on localhost (127.0.0.1) and port 4999
		// Socket s = new Socket("127.0.0.1",4999);
		// Socket s = new Socket("140.118.245.205",4999);
		Socket s = new Socket("140.118.218.159",4999);

		
		// Loop to exchange messages with the server 15 times
		for (int i=1; i<=3; i++ ) {
			
			// Prepare a message to send to the server
			String messagefromclient = "Client send " + i + ":" + "Hello";
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			
			// Send the message to the server
			pr.println(messagefromclient);
			pr.flush();
			
			System.out.println(messagefromclient);
			
			// Set up input stream to read data from the server
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			
			// Read a message from the server
			String str = bf.readLine();
			System.out.println(str);
			
			// Pause for 1 second
			System.out.println("------------------------------");
			TimeUnit.SECONDS.sleep(1);
		}
		
		// Close the socket
		s.close();
	}
}
