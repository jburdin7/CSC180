package Assignment5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import Lab2.Auction;

public class ConverterTest {

//	@Test
//	public void testFormat() {
//		Converter<Date> dates = new SimpleDateFormatConverter();
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2015);
//		cal.set(Calendar.MONTH, 9);
//		cal.set(Calendar.DATE, 21);
//		Date d = cal.getTime();
//		
//		String formatted = dates.format(d);
//		System.out.println(formatted);
//		Assert.assertEquals("21 October 2015", dates.format(d));
//	}
	
	@Test
	public void testTableRowFormat() {
		Converter<Auction> auctionConverter = new TableRowAuctionConverter();
		Assert.assertEquals("1 Pie Some delicious pie 1 null", (auctionConverter.format(new Auction(1, 1, "Pie", "Some delicious pie"))));
	}
	
	@Test
	public void testFormat() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DATE, 21);
		Date d = cal.getTime();
		
		SimpleDateFormatConverter s1 = new SimpleDateFormatConverter();
		SimpleDateFormatConverter s2 = new SimpleDateFormatConverter(new SimpleDateFormat("y"));
		SimpleDateFormatConverter s3 = new SimpleDateFormatConverter(new SimpleDateFormat("Y"), new SimpleDateFormat("s"));
		
		Assert.assertEquals("10", s1.format(d));
		Assert.assertEquals("2015", s2.format(d));


		Assert.assertEquals("Sun Mar 08 00:00:00 MST 1970", s1.parse("10"));
		Assert.assertEquals("Thu Jan 01 00:00:00 MST 2015", s2.parse("2015"));
		Assert.assertEquals("Sun Dec 31 00:00:00 MST 1939", s3.parse("40"));
	}

}
