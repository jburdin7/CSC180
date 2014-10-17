package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuctionCreateState implements Event {
	private final AuctionService auction;
	private BufferedReader buf;
	private String user;
	private String name;
	private String description;
	private Integer bid;
	
	public AuctionCreateState(AuctionService auction, BufferedReader buf, String user) {
		this.user = user;
		this.auction = auction;
		this.buf =  buf;
	}

	@Override
	public void show() {
		System.out.println(user + ", what is the name of the auction item?");
		try {
			this.name = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(user + ", please add a description of your auction item.");
		try {
			this.description = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter a starting price for you item. (Press enter to skip this step)");
		try {
			this.bid = Integer.parseInt(buf.readLine());
		} catch (IOException e) {
			this.bid = 0;
			System.out.println("The current bid amount has been defaulted to zero.");
		}
	}

	@Override
	public Event next() {
		auction.create(new Auction(0, bid, name, description));
		return new UserHomeState(auction, buf, user);
	}
}
