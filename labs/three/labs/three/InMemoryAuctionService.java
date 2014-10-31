package labs.three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InMemoryAuctionService implements AuctionService {
	private Stack<String> operators = new Stack<String>();
	private Stack<Predicate> searchTerms = new Stack<Predicate>();
	private Map<Integer, Auction> currentAuctions;
	private ArrayList<Auction> searchResults = new ArrayList<Auction>();
	private String oper;
	
	public InMemoryAuctionService() {
		currentAuctions = new HashMap<Integer, Auction>();
	}
	
	public InMemoryAuctionService(String fileName) throws IOException {
		Path p = Paths.get(fileName);
		byte[] b = Files.readAllBytes(p);
		String text = new String(b);
		String[] strArray = text.split("<h3 class=\"lvtitle\">");
		
		for(String str : strArray) {
			Pattern pat = Pattern.compile("<span class=\"g-b\">[\\s|$]([\\w|\\W|\\s]+?)</span>");
			Matcher m = pat.matcher(str);
			
			if(m.find()) {
				System.out.println(m.group(1));
			}
			
			pat = Pattern.compile("<div>Get it on or before <b>([\\w\\W\\|\\s]+)</b></div>");
			m = pat.matcher(str);
			
			if(m.find()) {
				System.out.println(m.group(1));
			}
			
		}

		
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
		
		
		searchResults = (ArrayList<Auction>) CollectionUtils.filter(currentAuctions.values(), searchTerms.pop());
		
//		for(Auction a : searchResults) {
//			if(a.getDate().before(new Date())) {
//				searchResults.remove(a);
//			}
//		}
		
		return searchResults.toArray(new Auction[searchResults.size()]);
	}

	@Override
	public void bid(String userName, Integer itemId) throws ParseException {//need check for wrong number
		if(userName == null || itemId == null) {
			throw new IllegalArgumentException();
		}
		for(Entry<Integer, Auction> entry : currentAuctions.entrySet()) {
			if(entry.getValue().getDate().before(new Date())) {
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
