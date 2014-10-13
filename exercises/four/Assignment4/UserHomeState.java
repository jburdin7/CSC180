package Assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserHomeState implements Assignment4.Event {
	private final AuctionService auction;
	private final BufferedReader buf;
	private String user;
	
	public UserHomeState(AuctionService auction, String user) {
		this.auction = auction;
		this.user = user;
		this.buf = new BufferedReader(new InputStreamReader(System.in));
	}
	public void show() {
		System.out.println(user + ", what would you like to search for?(hit enter to log out)");
	}

	public Assignment4.Event next() {
		String searchString = null;
		try {
			searchString = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(searchString.equals("")) {
			return null;
		}
		else {
			return new SearchResultsState(searchString, auction, user);
		}
	}

}
