package models;

public interface AuctionService {
	Auction[] search(String criteria);
	
	void bid(String username, Integer itemId);
	
	void delete(Integer id);
	
	Auction create(Auction auction);
	
	Auction retreive(Integer id);
	
	Auction update(Auction auction, Integer id);
}