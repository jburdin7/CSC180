package Assignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EventLoop {
	private AuctionService auction = new InMemoryAuctionService();
	private ArrayList<String> users = new ArrayList<String>();
	private Queue<Event> toDo = new LinkedList<Event>();
	private Queue<Event> done =  new LinkedList<Event>();
	
	public EventLoop() {
		toDo.add(new DefaultState(auction));
		toDo.add(new DefaultState(auction));
	}

	public void begin() throws IOException {
		while(true) {
			if(toDo.isEmpty() == true) {
				toDo.addAll(done);
				toDo.add(new DefaultState(auction));
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
