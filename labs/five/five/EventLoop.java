package labs.five;
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
	private AuctionService auctionService = new InMemoryAuctionService(new RecordReader("source.html"));
	private Queue<State> toDo = new LinkedList<State>();
	private Queue<State> done =  new LinkedList<State>();
	private Scanner buf = new Scanner(new InputStreamReader(System.in));
	
	public EventLoop() throws IOException, ParseException {
		toDo.add(new DefaultState(auctionService, buf));
	}

	public void begin() throws NumberFormatException, ParseException, IOException {
		while(true) {
			if(toDo.isEmpty() == true) {
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
