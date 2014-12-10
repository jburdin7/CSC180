

import java.util.Scanner;

public class DefaultState implements State {
	private ClientGui gui;
	private AuctionService auction;
	private Scanner buf;
	private String user;
	
	public DefaultState(AuctionService auction, Scanner buf, ClientGui gui) {
		this.gui = gui;
		this.user = null;
		this.auction = auction;
		this.buf = buf;
	}

	public void show() {
		
		System.out.println("New user , would you like to login?");
	}

	public State next() {
		user = buf.nextLine();
		if(user == null | user.equals("")) {
			return null;
		}
		else {
			UserHomeState hs = new UserHomeState(auction, buf, user);
			return hs;
		}
			
	}
}
