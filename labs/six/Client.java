

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;

public class Client {
	private String hostName = "localhost";
	private int portNumber = 9000;
	private Socket socket;
	
	public Client() throws UnknownHostException, IOException {
		socket = new Socket(hostName, portNumber);
	}
	
	public Auction retrieveRequest(Long id) throws ClassNotFoundException, IOException {
		Auction a = null;
		new PrintWriter(socket.getOutputStream(), true).println("r(" + id +")");
		new InputStreamReader(socket.getInputStream());
		if(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().equals("GO")) {
			a = (Auction) new ObjectInputStream(socket.getInputStream()).readObject();
		}
		return a;
	}
	
	public void createRequest(Auction auction) throws ClassNotFoundException, IOException {
		new PrintWriter(socket.getOutputStream(), true).println("c()");
		if(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().equals("GO")) {
			new ObjectOutputStream(socket.getOutputStream()).writeObject(auction);
		}
	}
	
	public void deleteRequest(Long id) throws IOException, ClassNotFoundException {
		new PrintWriter(socket.getOutputStream(), true).println("d("+ id +")");
		new InputStreamReader(socket.getInputStream()).read();
	}
	
	public Collection<Auction> searchRequest() throws IOException, ClassNotFoundException {
		new PrintWriter(socket.getOutputStream(), true).println("s()");
		new InputStreamReader(socket.getInputStream()).read();
		Collection<Auction> c = (Collection<Auction>) new ObjectInputStream(socket.getInputStream()).readObject();
		return c;
	}

	public void updateRequest(Auction auction, Long id) throws ClassNotFoundException, IOException {
		new PrintWriter(socket.getOutputStream(), true).println("c()");
		if(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().equals("GO")) {
			new ObjectOutputStream(socket.getOutputStream()).writeObject(auction);
		}
	}
}
