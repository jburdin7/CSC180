package Assignment4;

import org.junit.Before;
import org.junit.Test;

public class InMemoryAuctionServiceTest {
	AuctionService as = new InMemoryAuctionService();
	
	@Before
	public void setUp() {
		as.create(new Auction(0, 1, "Toaster"));
		as.create(new Auction(0, 1, "CoolToaster"));
		as.create(new Auction(0, 1, "Edible toast"));
		as.create(new Auction(0, 1, "Edible Water"));
	}
	
	@Test
	public void test() {
		Auction[] array = as.search("Toaster"); 
		for(Auction a : array) {
			System.out.println(a.getName());
		}
		
		System.out.println("");
		
		array = as.search("Edible"); 
		for(Auction a : array) {
			System.out.println(a.getName());
		}
	}
}
