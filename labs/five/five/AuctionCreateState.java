package labs.five;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuctionCreateState implements State {
	private final AuctionService auction;
	private Scanner buf;
	private String user;
	private String name;
	private String description;
	private Double bid;
	private long endsBy;
	
	public AuctionCreateState(AuctionService auction, Scanner buf, String user) {
		this.user = user;
		this.auction = auction;
		this.buf =  buf;
	}

	@Override
	public void show() {
		String str = null;
		
		System.out.println(user + ", what is the name of the auction item?");
		this.name = buf.nextLine();
		
		System.out.println(user + ", please add a description of your auction item.");
		this.description = buf.nextLine();
		try {
			System.out.println("Enter a starting price for you item. (Press enter to skip this step)");
			this.bid = Double.parseDouble(buf.nextLine());
		}
		catch(NumberFormatException e) {
			//e.printStackTrace();
			this.bid = 0D;
		}
		
		System.out.println("Enter the date you want you auction to end. (MM/dd/yyyy or MM.dd.yyyy)");
		str = buf.nextLine();
		Date d = new Date();
		
		Pattern p1 = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
		Pattern p2 = Pattern.compile("(\\d{2})\\.(\\d{2})\\.(\\d{4})");
		Matcher m1 = p1.matcher(str);
		Matcher m2 = p2.matcher(str);
		Calendar cal = Calendar.getInstance();
		
		if(m1.find()) {
			cal.set(Calendar.YEAR, Integer.parseInt(m1.group(3)));
			cal.set(Calendar.MONTH, Integer.parseInt(m1.group(1)));
			cal.set(Calendar.DATE, Integer.parseInt(m1.group(2)));
			d = cal.getTime();
		}
		else if(m2.find()) {
			cal.set(Calendar.YEAR, Integer.parseInt(m2.group(3)));
			cal.set(Calendar.MONTH, Integer.parseInt(m2.group(1)));
			cal.set(Calendar.DATE, Integer.parseInt(m2.group(2)));
			d = cal.getTime();
		}
		else {
			d = new Date(cal.getTimeInMillis() + (7 * 24 * 60 * 60 * 1000));
		}
		
		auction.create(new Auction(0L, bid, name, description, user, d));
	}

	@Override
	public State next() {
		return new UserHomeState(auction, buf, user);
	}
}
