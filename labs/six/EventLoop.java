
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EventLoop {
	private ClientGui gui = new ClientGui(this);
	private AuctionService auctionService = new InMemoryAuctionService();
	private Queue<State> toDo = new LinkedList<State>();
	private Queue<State> done =  new LinkedList<State>();
	private Scanner buf = new Scanner(new InputStreamReader(System.in));
	
	public EventLoop() throws IOException, ParseException {
		toDo.add(new DefaultState(auctionService, buf, gui));
	}

	public void begin() throws NumberFormatException, ParseException, IOException {
		Thread t = new Thread(gui);
		t.start();
		
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

	public void searchState() {
		// TODO Auto-generated method stub
		Auction[] auctions = auctionService.search("");
		System.out.println("yes");
		gui.showSearchPanel(auctions);
	}

	public void bidState() {
		// TODO Auto-generated method stub
		
	}

	public void editState() {
		// TODO Auto-generated method stub
		
	}

	public void deleteState() {
		// TODO Auto-generated method stub
		
	}

	public void createState() {
		// TODO Auto-generated method stub
		
	}
	
	
}
