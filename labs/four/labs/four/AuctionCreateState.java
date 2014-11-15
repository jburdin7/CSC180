package labs.four;

import java.text.SimpleDateFormat;
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
		System.out.println("Enter a starting price for you item. (Press enter to skip this step)");
		this.bid = Double.parseDouble(buf.nextLine());
		System.out.println("Enter the date you want you auction to end. (MM/dd/yyyy or MM.dd.yyyy)");
		str = buf.nextLine();
		
		Pattern p1 = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
		Pattern p2 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
		Matcher m1 = p1.matcher(str);
		Matcher m2 = p2.matcher(str);
		Calendar cal = Calendar.getInstance();
		if(m1.find()) {
			cal.set(Calendar.YEAR, Integer.parseInt(m1.group(3)));
			cal.set(Calendar.MONTH, Integer.parseInt(m1.group(2)));
			cal.set(Calendar.DATE, Integer.parseInt(m1.group(1)));
		}
		else if(m2.find()) {
			cal.set(Calendar.YEAR, Integer.parseInt(m2.group(3)));
			cal.set(Calendar.MONTH, Integer.parseInt(m2.group(2)));
			cal.set(Calendar.DATE, Integer.parseInt(m2.group(1)));
		}
		auction.create(new Auction(0L, bid, name, description, user, cal.getTime(), 0));
	}

	@Override
	public State next() {
		return new UserHomeState(auction, buf, user);
	}
}
