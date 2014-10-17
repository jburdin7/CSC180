package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class SearchResultsState implements Lab2.Event {
	private BufferedReader buf;
	private AuctionService auction;
	private Auction[] auctions;
	private String searchString;
	private String user;
	
	public SearchResultsState(AuctionService auction, BufferedReader buf, String user) {
		this.buf = buf;
		this.auction = auction;
		this.user = user;
	}

	public void show() {
		System.out.println(user + ", what would you like to search for?");
		try {
			searchString = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		auctions =  auction.search(searchString);
		System.out.println(user + ", here are your search results:");
		System.out.println("------------Search Results------------");
		
		for(Auction auction : auctions) {
			System.out.println("|   " + auction.toString());
		}
		System.out.println("-------------------------------------- \n");
		System.out.println(user + ", what item would you like to bid on? (hit enter to return Home or enter another search)");
	}

	public Lab2.Event next() {
		try {
			searchString = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(searchString == null || searchString.equals("")) {
			return new UserHomeState(auction, buf, user);
		}
		else {
			try {
				auction.bid(user, Integer.parseInt(searchString));
				return this;
			}
			catch(Exception e) {
				return new SearchResultsState(auction, buf, user);
			}
		}
	}

}
