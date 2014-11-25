package labs.five;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Stack;

public class InMemoryAuctionService implements AuctionService {
	private FileBasedDataSource source = new FileBasedDataSource();
	private Stack<String> operators = new Stack<String>();
	private Stack<Predicate> searchTerms = new Stack<Predicate>();
	private ArrayList<Auction> searchResults = new ArrayList<Auction>();
	private String oper;
	private Client client;
	
	public InMemoryAuctionService() {
	}
	
	public InMemoryAuctionService(RecordReader rr) {
//		while ( rr.hasNext() ) {
//			Auction a = rr.next();
//			try {
//				source.create(a);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			if ( a.getId() > mapKey ) {
//				mapKey = a.getId() + 1;
//			}
//		}
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
		
		try {
			Collection<Auction> temp = new Client().searchRequest();
			Predicate temp2 = searchTerms.pop();
			
			searchResults = new ArrayList<Auction>(CollectionUtils.filter(temp, temp2));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(Auction a : searchResults) {
//			if(a.getDate().before(new Date())) {
//				searchResults.remove(a);
//			}
//		}
		
		return searchResults.toArray(new Auction[searchResults.size()]);
	}

	@Override
	public void bid(String userName, Long itemId) throws ParseException {//need check for wrong number
		if(userName == null || itemId == null) {
			throw new IllegalArgumentException();
		}
		try {
			for(Auction auction : new Client().searchRequest()) {
				if(auction.getEndsBy().before(new Date())) {
					throw new ExpiredDateException();
				}
				
				if(auction.getId() == itemId) {
					auction.setCurrentBid(auction.getCurrentBid() + 1);
					auction.setOwner(userName);
				}
//		for(Auction a : currentAuctions) {
//			if(a.getId() == itemId) {
//				a.setCurrentBid(a.getCurrentBid() + 1);
//				a.setOwner(userName);
//			}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Auction create(Auction auction) {
		Long mapKey = source.countIDs();
		Auction a = new Auction(++mapKey, auction.getCurrentBid(), auction.getName(), auction.getDescription(), auction.getEndsBy());
		try {
			new Client().createRequest(a);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void delete(Long id) {
		try {
			for(Auction auction : new Client().searchRequest()) {
				if(auction.getId() == id) {
					new Client().deleteRequest(id);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Auction retreive(Long id) {
		try {
			for(Auction auction : new Client().searchRequest()) {
				if(auction.getId() == id) {
					return auction;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ObjectNotFoundException();
	}

	@Override
	public Auction update(Auction auction, Long id) {
		try {
			new Client().updateRequest(auction, id);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auction;
	}
}
