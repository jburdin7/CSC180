import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
	private Map<Long, Auction> auctions = new HashMap<Long, Auction>();
	private List<Thread> clients = new ArrayList<Thread>();
	private Long mapKey = (long) 0;
	private Socket clientSocket;
	private ServerSocket ss;
	
	public Server() throws IOException {
		ss = new ServerSocket(9000);
	}
	
	public  void run() throws IOException, ClassNotFoundException {
		
		while( true ) {
			clientSocket = ss.accept();
			if(clientSocket != null) {
				Thread t = new Thread(new ServerThread());
				clients.add(t);
				t.start();
			}
			
			for(Thread t : clients) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized <T> void parseToCommand(String typeOfCommand, T arg) throws IOException, ClassNotFoundException {
		switch(typeOfCommand) {
		case"c":
			Auction auction = (Auction) new ObjectInputStream(clientSocket.getInputStream()).readObject();
			auctions.put(mapKey++, auction);
			break;
		case"u":
			auction = (Auction) new ObjectInputStream(clientSocket.getInputStream()).readObject();
			if(auction.getLastModifiedDate() != auctions.get(arg).getLastModifiedDate()) {
				auctions.remove(Long.parseLong((String) arg));
				auction.setLastModifiedDate(new Date());
				auctions.put(Long.parseLong((String) arg), auction);
			}
			break;
		case"d":
			auctions.remove(Long.parseLong((String) arg));
			break;
		case"r":
			Auction auc = auctions.get(Long.parseLong((String) arg));
			new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(auc);
			break;
		case"s":
			ArrayList<Auction> aucArry = new ArrayList<Auction>();
			for(Auction item : auctions.values()) {
				aucArry.add(item);
			}
			new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(aucArry);
			break;
		}
	}
	
	public class ServerThread implements Runnable {

		@Override
		public void run() {
			Pattern p = Pattern.compile("(\\w)\\((.*)\\)");
			try {
				PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				String request = buf.readLine();
				Matcher m = p.matcher(request);
				
				if(m.find()) {
					pw.println("GO");
					pw.flush();
					parseToCommand(m.group(1), m.group(2));
				}
				else {
					pw.println("Could not parse command");
					pw.flush();
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
