package models;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuctionService implements AuctionService {

	private ArrayList<Auction> currentAuctions;
	
	public InMemoryAuctionService() {
		currentAuctions = new ArrayList<Auction>();
		currentAuctions.add(new Auction("1", 100, "game"));
		currentAuctions.add(new Auction("2", 1000, "car"));
		currentAuctions.add(new Auction("3", 10000, "house"));
		currentAuctions.add(new Auction("4", 110, "coolgame"));
	}
	
	@Override
	public Auction[] search(String criteria) {
		ArrayList<Auction> searchResults = new ArrayList<Auction>();
		for(Auction auction : currentAuctions) {
			if(auction.getName().contains(criteria.toLowerCase())) {
				searchResults.add(auction);
			}
		}
		return searchResults.toArray(new Auction[searchResults.size()]);
	}

	@Override
	public void bid(String username, int itemId) {
		for(Auction a : currentAuctions) {
			if(Integer.parseInt(a.getId()) == itemId) {
				a.setCurrentBid(a.getCurrentBid() + 1);
				a.setOwner(username);
			}
		}
	}
}
