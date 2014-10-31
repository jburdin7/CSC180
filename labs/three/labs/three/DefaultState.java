package labs.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultState implements State {
	private AuctionService auction;
	private BufferedReader buf;
	private String user;
	
	public DefaultState(AuctionService auction, BufferedReader buf) {
		this.user = null;
		this.auction = auction;
		this.buf = buf;
	}

	public void show() {
		System.out.println("New user , would you like to login?");
	}

	public State next() {
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
			UserHomeState hs = new UserHomeState(auction, buf, user);
			return hs;
		}
	}
}
