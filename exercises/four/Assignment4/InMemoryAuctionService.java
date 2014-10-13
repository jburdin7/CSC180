package Assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class InMemoryAuctionService implements AuctionService {

	private Map<Integer, Auction> currentAuctions;
	private Predicate predicate;
	
	public InMemoryAuctionService() {
		currentAuctions = new HashMap<Integer, Auction>();
	}
	
	@Override
	public Auction[] search(String criteria) {
		predicate = new ContainsPredicate(criteria);
		ArrayList<Auction> searchResults = new ArrayList<Auction>();
		searchResults = (ArrayList<Auction>) CollectionUtils.filter(currentAuctions.values(), predicate);
		return searchResults.toArray(new Auction[searchResults.size()]);
	}

	@Override
	public void bid(String userName, Integer itemId) {//need check for wrong number
		if(userName == null || itemId == null) {
			throw new IllegalArgumentException();
		}
		for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == itemId) {
				entry.getValue().setCurrentBid(entry.getValue().getCurrentBid() + 1);
				entry.getValue().setOwner(userName);
			}
//		for(Auction a : currentAuctions) {
//			if(a.getId() == itemId) {
//				a.setCurrentBid(a.getCurrentBid() + 1);
//				a.setOwner(userName);
//			}
		}
	}

	@Override
	public Auction create(Auction auction) {
		int mapKey = 0;
		if(currentAuctions.isEmpty() == true) {
			auction = currentAuctions.put(mapKey, auction);
		}
		else {
			for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
				if(entry.getKey() != mapKey) {
					currentAuctions.put(mapKey, auction);
					auction = currentAuctions.get(mapKey);
					return auction;
				}
				mapKey++;
			}
			currentAuctions.put(mapKey + 1, auction);
		}
		return auction;
	}

	@Override
	public void delete(Integer id) {
		for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				currentAuctions.remove(entry.getKey());
			}
		}
	}

	@Override
	public Auction retreive(Integer id) {
		for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				return entry.getValue();
			}
		}
		throw new ObjectNotFoundException();
	}

	@Override
	public Auction update(Auction auction, Integer id) {
		for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				return entry.getValue();
			}
		}
		throw new IdMismatchException();
	}
}
