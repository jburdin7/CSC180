package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultState implements models.Event {
	private AuctionService auction;
	private final BufferedReader buf;
	private String user;
	
	public DefaultState(AuctionService auction) {
		this.user = null;
		this.auction = auction;
		this.buf = new BufferedReader(new InputStreamReader(System.in));
	}

	public void show() {
		System.out.println("New user , would you like to login?");
	}

	public models.Event next() {
		try {
			user = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user == null || user.equals("")) {
			return null;
		}
		else {
			return new UserHomeState(auction, user);
		}
	}
}
