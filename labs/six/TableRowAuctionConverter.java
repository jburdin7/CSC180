

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class TableRowAuctionConverter implements Converter<Auction> {
	private DateFormat df = new SimpleDateFormat("MM:dd:yyyy");
	
	@Override
	public Auction parse(String fromString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(Auction fromT) {
		String formattedString = Padder.pad(fromT.getId(), 18) + Padder.pad(fromT.getName(), 100) +
				Padder.pad(fromT.getDescription(),50) + Padder.pad(NumberFormat.getCurrencyInstance().format(fromT.getCurrentBid()), 12) +
				Padder.pad(fromT.getOwner(), 15) + Padder.pad(fromT.getNumberOfBidsRemaining(), 12) + 
				Padder.pad(df.format(fromT.getEndsBy()), 15);
		return formattedString;
	}

}
