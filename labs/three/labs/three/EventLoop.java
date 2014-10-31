package labs.three;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class EventLoop {
	private AuctionService auctionService = new InMemoryAuctionService();
	private Queue<State> toDo = new LinkedList<State>();
	private Queue<State> done =  new LinkedList<State>();
	private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
	
	public EventLoop() throws IOException {
		InMemoryAuctionService s = new InMemoryAuctionService();
		toDo.add(new DefaultState(auctionService, buf));
	}

	public void begin() throws IOException {
		while(true) {
			if(toDo.isEmpty() == true) {
				toDo.add(new DefaultState(auctionService, buf));
				toDo.addAll(done);
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
