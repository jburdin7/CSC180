package Lab2;

public class AndPredicate implements Predicate<Auction>{
	Predicate searchOne;
	Predicate searchTwo;

	public AndPredicate(Predicate searchOne, Predicate searchTwo) {
		this.searchOne = searchOne;
		this.searchTwo = searchTwo;
	}
	
	@Override
	public boolean evaluate(Auction auction) {
		System.out.println("nuuu");
		if(searchOne.evaluate(auction) == true && searchTwo.evaluate(auction) == true) {
			return true;
		}
		else {
			return false;
		}
	}

}
