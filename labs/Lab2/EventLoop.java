package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EventLoop {
	private AuctionService auction = new InMemoryAuctionService();
	private ArrayList<String> users = new ArrayList<String>();
	private Queue<Event> toDo = new LinkedList<Event>();
	private Queue<Event> done =  new LinkedList<Event>();
	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	
	public EventLoop() {
		toDo.add(new DefaultState(auction, buf));
		toDo.add(new DefaultState(auction, buf));
		auction.create(new Auction(0, 0, "Lovely Toast", "bleh"));
		auction.create(new Auction(0, 0, "Yummy Pie", "bleh"));
		auction.create(new Auction(0, 0, "Bleh Toast", "bleh"));
	}

	public void begin() throws IOException {
		while(true) {
			if(toDo.isEmpty() == true) {
				toDo.addAll(done);
				toDo.add(new DefaultState(auction, buf));
				done.clear();
			}
			if(toDo.peek() == null) {
				toDo.remove();
			}
			else {
				toDo.peek().show();
				done.add(toDo.poll().next());
			}
		}
	}
}
