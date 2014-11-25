package labs.five;
import java.text.ParseException;


public interface AuctionService {
	Auction[] search(String criteria);
	
	void bid(String username, Long d) throws ParseException;
	
	void delete(Long id);
	
	Auction create(Auction auction);
	
	Auction retreive(Long Long);
	
	Auction update(Auction auction, Long id);
}