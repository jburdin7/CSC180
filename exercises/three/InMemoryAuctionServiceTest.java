package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import models.Auction;
import models.AuctionService;
import models.InMemoryAuctionService;

import org.junit.Before;
import org.junit.Test;

public class InMemoryAuctionServiceTest {
	private AuctionService service = new InMemoryAuctionService();
	private Map<String, Object> myMap = new HashMap<String, Object>();

	@Before
	public void setUp() {
		myMap.put("shiny", String.class);
	}
	
	@Test
	public void test() {
		service.create(new Auction(0, 50, "toaster", myMap));		
		service.retreive(0);
		
		service.bid("bob", 0);
		Auction[] actual = service.search("toaster");
		for(int i = 0; i < actual.length; i++) {
			System.out.println(actual[i]);
		}
		
		service.delete(0);
		service.retreive(0);
	}
}
