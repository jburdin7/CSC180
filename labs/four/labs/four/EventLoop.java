package labs.four;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EventLoop {
	private AuctionService auctionService = new InMemoryAuctionService();
	private Queue<State> toDo = new LinkedList<State>();
	private Queue<State> done =  new LinkedList<State>();
	private Scanner buf = new Scanner(new InputStreamReader(System.in));
	
	public EventLoop() throws IOException, ParseException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		auctionService.create(new Auction(1L, 1D, "pie", "pie", "pie", cal.getTime(), 1));
		auctionService.create(new Auction(1L, 1D, "taco", "taco", "taco", cal.getTime(), 1));
		toDo.add(new DefaultState(auctionService, buf));
	}

	public void begin() throws NumberFormatException, ParseException, IOException {
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
