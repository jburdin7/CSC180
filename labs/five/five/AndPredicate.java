package labs.five;

public class AndPredicate implements Predicate<Auction>{
	Predicate<Auction> searchOne;
	Predicate<Auction> searchTwo;

	public AndPredicate(Predicate<Auction> searchOne, Predicate<Auction> searchTwo) {
		this.searchOne = searchOne;
		this.searchTwo = searchTwo;
	}
	
	@Override
	public boolean evaluate(Auction auction) {
		if(searchOne.evaluate(auction) == true && searchTwo.evaluate(auction) == true) {
			return true;
		}
		else {
			return false;
		}
	}

}
