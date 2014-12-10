

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordReader {
	private static final Pattern AUCTION_PATTERN = Pattern.compile(
			"<h3 class=\"lvtitle\"><a .*?>(?:<span class=\"newly\">New listing</span>.*?)?(.*?)</a>.*?\\$([0-9,]+\\.\\d{2}).*?((\\d+) bid.*?)?(timeMs=\"(\\d+)\".*?)?Item: (\\d+)", Pattern.DOTALL);
	
	private Matcher m;
	
	public RecordReader(String fileName) {
		try {
			Path p = Paths.get("src.html");
			byte[] b = Files.readAllBytes(p);
			String text = new String(b);
			m = AUCTION_PATTERN.matcher(text);
		} catch ( IOException e ) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean hasNext() {
		return m.find();
	}
	
	public Auction next() {
		String title = m.group(1).trim();
		double price = Double.parseDouble(m.group(2).replace(",", ""));
		int numberOfBids = parseInto(m.group(4), 0, Integer.class);
		long endTimeMillis = parseInto(m.group(6), System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000, Long.class);
		long id = Long.parseLong(m.group(7));
		return new Auction(id, price, title, "", "", numberOfBids, endTimeMillis);
	}
	
	private <T> T parseInto(String toBeParsed, T defaultValue, Class<T> clazz) {
		try {
			return clazz.getConstructor(String.class).newInstance(toBeParsed);
		} catch ( Exception e ) {
			return defaultValue;
		}
	}
}
