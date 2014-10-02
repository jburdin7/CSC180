package Models;

public interface AuctionService {
	Auction[] search(String criteria);
	
	void bid(String username, int itemId);
}
