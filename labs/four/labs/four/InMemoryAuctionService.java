package labs.four;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class InMemoryAuctionService implements AuctionService {
	private FileBasedDataSource fbds = new FileBasedDataSource();
	private Stack<String> operators = new Stack<String>();
	private Stack<Predicate> searchTerms = new Stack<Predicate>();
	private ArrayList<Auction> searchResults = new ArrayList<Auction>();
	private String oper;
	private Long mapKey = (long) 0;
	
	public InMemoryAuctionService() {
		
	}
	
	@Override
	public Auction[] search(String criteria) {
		String[] strArray = criteria.split("((?<=OR)|(?<=AND)|(?=OR)|(?=AND))");
		for(String str : strArray) {
			if(str.trim().equals("AND")) {
				operators.push("AND");
			}
			else if(str.trim().equals("OR")) {
				if(!operators.isEmpty()) {
					if(operators.peek().equals("AND")) {
						oper = operators.pop();
						Predicate p1 = searchTerms.pop();
						Predicate p2 = searchTerms.pop();
						searchTerms.push(new AndPredicate(p1, p2));
					}
					operators.push("OR");
				}
				else {
					operators.push("OR");
				}
			}
			else {
				searchTerms.push(new ContainsPredicate(str.trim()));
			}
		}
		
		while(operators.size() >= 1) {
			if(operators.peek().equals("OR")) {
				oper = operators.pop();
				Predicate p1 = searchTerms.pop();
				Predicate p2 = searchTerms.peek();
				searchTerms.push(new OrPredicate(p1, p2));
			}
			else if(operators.peek().equals("AND")) {
				oper = operators.pop();
				Predicate p1 = searchTerms.pop();
				Predicate p2 = searchTerms.pop();
				searchTerms.push(new AndPredicate(p1, p2));
			}
		}
		
		//searchResults = (ArrayList<Auction>) CollectionUtils.filter(fbds.getAuctions(), searchTerms.pop());
		
		for(Auction a : searchResults) {
			Calendar cal = Calendar.getInstance();
			if(a.getEndsBy().after(cal.getTime())) {
				searchResults.remove(a);
			}
		}
		
		return searchResults.toArray(new Auction[searchResults.size()]);
	}

	@Override
	public void bid(String userName, Long itemId) throws ParseException {//need check for wrong number
		if(userName == null || itemId == null) {
			throw new IllegalArgumentException();
		}
		for(Entry<Long, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getEndsBy().before(new Date())) {
				throw new ExpiredDateException();
			}
			
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
		Auction a = new Auction(++mapKey, auction.getCurrentBid(), auction.getName(), auction.getCreator(), auction.getDescription(), auction.getEndsBy(), mapKey);
		currentAuctions.put(a.getId(), a);
		return a;
	}

	@Override
	public void delete(Long id) {
		for(Entry<Long, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				currentAuctions.remove(entry.getKey());
			}
		}
	}

	@Override
	public Auction retreive(Long id) {
		for(Entry<Long, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				return entry.getValue();
			}
		}
		throw new ObjectNotFoundException();
	}

	@Override
	public Auction update(Auction auction, Long id) {
		for(Entry<Long, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getId() == id) {
				return entry.getValue();
			}
		}
		throw new IdMismatchException();
	}
}
