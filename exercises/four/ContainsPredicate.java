package Assignment4;


public class ContainsPredicate implements Predicate<Auction> {
	private String criteria;

	public ContainsPredicate(String criteria)  {
		this.criteria = criteria;
	}
	
	@Override
	public boolean evaluate(Auction auction) {
		return auction.getName().toLowerCase().contains(criteria.toLowerCase());
	}
}
