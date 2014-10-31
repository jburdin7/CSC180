package labs.three;
import java.text.ParseException;


public class TableRowAuctionConverter implements Converter<Auction> {

	@Override
	public Auction parse(String fromString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(Auction fromT) {
		String formattedString = String.format("%-4d%-12s%-12s%-12d%-12s%s\n", fromT.getId(),
				fromT.getName(), fromT.getDescription(), fromT.getCurrentBid(), fromT.getOwner(), fromT.getDate());
		return formattedString;
	}

}
