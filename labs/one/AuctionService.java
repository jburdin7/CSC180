package models;

public interface AuctionService {
	Auction[] search(String criteria);
	
	void bid(String username, int itemId);
}