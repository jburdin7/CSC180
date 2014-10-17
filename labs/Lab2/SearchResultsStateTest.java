package Lab2;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class SearchResultsStateTest {
	private final AuctionService as = new AuctionService() {
		private Auction dummy = new Auction(1, 0, "dummy", "dummy");
		@Override
		public Auction[] search(String criteria) {
			return new Auction[] { dummy };
		}

		@Override
		public void bid(String username, Integer itemId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Integer id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Auction create(Auction auction) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Auction retreive(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Auction update(Auction auction, Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
//	@Test
//	public void testEmptyResponse() {
//		SearchResultsState srs = new SearchResultsState(as, new Scanner("/n"), "John");
//		Event s = srs.next();
////		if(!(s instanceof UserHomeState)) {
////			Assert.fail("Should have returned a UserHomeState");
////		}
//		Assert.assertTrue("Should have returned a UserHomeState", s instanceof UserHomeState);
//		
//	}
//	
//	@Test
//	public void testBidResponse() {
//		SearchResultsState srs = new SearchResultsState(as, new Scanner("1234"), "John");
//		Event s = srs.next();
//		
//		Assert.assertTrue("Should have returned a SearchResultsState", s instanceof SearchResultsState);
//	}
	
	@Test
	public void testSearchResponse()  {
		
	}


}
