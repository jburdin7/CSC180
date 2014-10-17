package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserHomeState implements Lab2.Event {
	private final AuctionService auction;
	private final BufferedReader buf;
	private String user;
	private String input;
	
	public UserHomeState(AuctionService auction, BufferedReader buf, String user) {
		this.auction = auction;
		this.user = user;
		this.buf = buf;
	}
	public void show() {
		System.out.println(user + ", would you like to do a COMPLEX SEARCH, or add new auction ITEM(hit enter to log out)");
	}

	public Lab2.Event next() {
		String input = null;
		try {
			input = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(input) {
			case"COMPLEX SEARCH": {
				return new SearchResultsState(auction, buf, user);
			}
			case"ITEM": {
				return new AuctionCreateState(auction, buf, user);	
			}
			case"": {
				return null;
			}
			default: {
				System.out.println("I'm sorry, I couldn't process your request. Please try again.");
				return this;
			}
		}
	}

}
