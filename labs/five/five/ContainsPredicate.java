package labs.five;

public class ContainsPredicate implements Predicate<Auction> {
	private String criteria;

	public ContainsPredicate(String criteria)  {
		this.criteria = criteria;
	}
	
	@Override
	public boolean evaluate(Auction auction) {
		if(auction.getName().toLowerCase().contains(criteria.toLowerCase()) || auction.getDescription().toLowerCase().contains(criteria.toLowerCase())) {
			return true;
		}
		else {
			return false;
		}
	}
}
