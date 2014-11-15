package labs.three;

import java.io.IOException;
import java.text.ParseException;

public class Driver {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {		
		EventLoop event = new EventLoop();
		event.begin();
	}
}
