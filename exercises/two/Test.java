package Models;

import java.awt.List;

public class Test {

	public static void main(String[] args) {
		AuctionService as = new InMemoryAuctionService();
		
		Auction[] list = as.search("T.V");
		System.out.println(list[0].toString());
		
		//checks the bid method to see if it updates properly
		as.bid("bob", 1);
		list = as.search("T.V");
		System.out.println(list[0].toString());
		System.out.println();
		
		//check the equals method
		Auction a = list[0];
		System.out.println(list[0].equals(a));
		
		System.out.println(list[0].equals(new Auction(null, 0, null)));
	}

}
