package labs.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AuctionCreateState implements State {
	private final AuctionService auction;
	private BufferedReader buf;
	private String user;
	private String name;
	private String description;
	private Integer bid;
	private long endsBy;
	
	public AuctionCreateState(AuctionService auction, BufferedReader buf, String user) {
		this.user = user;
		this.auction = auction;
		this.buf =  buf;
	}

	@Override
	public void show() {
		String str = null;
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
		System.out.println("Enter the date you want you auction to end. (MM/dd/yyyy or MM.dd.yyyy)");
		try {
			str = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormatConverter sdfc1 = new SimpleDateFormatConverter(new SimpleDateFormat("MM/dd/yyyy"));
		SimpleDateFormatConverter sdfc2 = new SimpleDateFormatConverter(new SimpleDateFormat("MM.dd.yyyy"));
		
		if(sdfc1.parse(str) != null) {
			sdfc1.parse(str);
		}
		else if(sdfc1.parse(str) != null) {
			sdfc2.parse(str);
		}
		else {
			
		}
	}

	@Override
	public State next() {
		auction.create(new Auction(0, bid, name, description, endsBy));
		return new UserHomeState(auction, buf, user);
	}
}
