package labs.three;

public class OrPredicate implements Predicate<Auction>{
	private Predicate<Auction> searchOne;
	private Predicate<Auction> searchTwo;

	public OrPredicate(Predicate<Auction> searchOne, Predicate<Auction> searchTwo) {
		this.searchOne = searchOne;
		this.searchTwo = searchTwo;
	}
	
	@Override
	public boolean evaluate(Auction auction) {
		if(searchOne.evaluate(auction) || searchTwo.evaluate(auction)) {
			return true;
		}
		else {
			return false;
		}
	}

}
