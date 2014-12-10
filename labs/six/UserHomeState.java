
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class UserHomeState implements State {
	private final AuctionService auction;
	private final Scanner buf;
	private String user;
	private String input;
	
	public UserHomeState(AuctionService auction, Scanner buf, String user) {
		this.auction = auction;
		this.user = user;
		this.buf = buf;
	}

	public void show() {
		System.out.println("~Menu~");
		System.out.println("1 - Complex Search");
		System.out.println("2 - Create Auction Item");
		System.out.println("3 - Logout");
	}

	public State next() {
		String input = null;
		input = buf.nextLine();
		
		switch(input) {
			case"1": {
				return new SearchResultsState(auction, buf, user);
			}
			case"2": {
				return new AuctionCreateState(auction, buf, user);	
			}
			case"3": {
				return null;
			}
			default: {
				System.out.println("I'm sorry, I couldn't process your request. Please try again.");
				return this;
			}
		}
	}

}
