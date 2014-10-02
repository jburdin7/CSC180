package Models;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuctionService implements AuctionService {

	private ArrayList<Auction> currentAuctions;
	
	public InMemoryAuctionService() {
		currentAuctions = new ArrayList<Auction>();
		currentAuctions.add(new Auction("1", 100, "T.V"));
		currentAuctions.add(new Auction("2", 1000, "Car"));
		currentAuctions.add(new Auction("3", 10000, "House"));
	}
	
	@Override
	public Auction[] search(String criteria) {
		ArrayList<Auction> searchResults = new ArrayList<Auction>();
		for(Auction auction : currentAuctions) {
			if(auction.getName().equalsIgnoreCase(criteria)) {
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
