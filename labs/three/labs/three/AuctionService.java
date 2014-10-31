package labs.three;
import java.text.ParseException;


public interface AuctionService {
	Auction[] search(String criteria);
	
	void bid(String username, Integer itemId) throws ParseException;
	
	void delete(Integer id);
	
	Auction create(Auction auction);
	
	Auction retreive(Integer id);
	
	Auction update(Auction auction, Integer id);
}