package labs.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
	private FileBasedDataSource fbds = new FileBasedDataSource();
	private Socket clientSocket;
	
	public  void run() throws IOException, ClassNotFoundException {
		Pattern p = Pattern.compile("(\\w)\\((.*)\\)");
		
		ServerSocket ss = new ServerSocket(8000);
		while( true ) {
			//wait for a request from a client
			clientSocket = ss.accept();
			//read a request in, and serialize it into a java request
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
			//ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//perform that request, and issue a response
			String request = buf.readLine();
			Matcher m = p.matcher(request);
			
			if(m.find()) {
				pw.println("GO");
				parseToCommand(m.group(1), m.group(2));
			}
			else {
				pw.println("Could not parse command");
				pw.flush();
			}
			
			//oos.writeObject(new Food("pie"));
			//oos.flush();
		}
	}
	
	public <T> void parseToCommand(String typeOfCommand, T arg) throws IOException, ClassNotFoundException {
		switch(typeOfCommand) {
		case"c":
			Auction auction = (Auction) new ObjectInputStream(clientSocket.getInputStream()).readObject();
			fbds.create(auction);
			new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(auction);
			break;
		case"u":
			auction = (Auction) new ObjectInputStream(clientSocket.getInputStream()).readObject();
			fbds.update(auction, (Long)arg);
			break;
		case"d":
			fbds.delete((Long)arg);
			break;
		case"r":
			Auction a = fbds.retrieve(Long.parseLong(arg.toString()));
			new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(a);
			break;
		case"s":
			new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(fbds.getAuctions());
			break;
		}
		
		
	}
}
