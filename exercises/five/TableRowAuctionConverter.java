package Assignment5;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import Lab2.Auction;

public class TableRowAuctionConverter implements Converter<Auction> {

	@Override
	public Auction parse(String fromString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(Auction fromT) {
		
		String formattedString = fromT.getId() + " " + fromT.getName() + " " + fromT.getDescription() + " " + fromT.getCurrentBid() + " " + fromT.getOwner();
		return formattedString;
	}

}
