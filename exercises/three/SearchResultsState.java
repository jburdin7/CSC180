package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchResultsState implements models.Event {
	private final BufferedReader buf;
	private AuctionService auction;
	private Auction[] auctions;
	private String searchString;
	private String user;
	
	public SearchResultsState(String searchString, AuctionService auction, String user) {
		this.buf = new BufferedReader(new InputStreamReader(System.in));
		this.auction = auction;
		this.searchString = searchString;
		this.user = user;
	}

	public void show() {
		auctions =  auction.search(searchString);
		System.out.println(user + ", here are your search results:");
		System.out.println("------------Search Results------------");
		
		for(Auction auction : auctions) {
			System.out.println("|   " + auction.toString());
		}
		System.out.println("-------------------------------------- \n");
		System.out.println(user + ", what item would you like to bid on? (hit enter to return Home or enter another search)");
	}

	public models.Event next() {
		String bidItem = null;
		try {
			bidItem = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bidItem.equals("")) {
			return new UserHomeState(auction, user);
		}
		else {
			try {
				auction.bid(user, Integer.parseInt(bidItem));
			}
			catch(Exception e) {
				return new SearchResultsState(bidItem, auction, user);
			}
			return new SearchResultsState(searchString, auction, user);
		}
	}

}
