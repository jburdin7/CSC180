package labs.three;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Test;


public class SimpleDateFormatConverterTest {

	@Test
	public void test() {
		SimpleDateFormatConverter sdfc1 = new SimpleDateFormatConverter(new SimpleDateFormat("MM/dd/yyyy"));
		SimpleDateFormatConverter sdfc2 = new SimpleDateFormatConverter(new SimpleDateFormat("MM.dd.yyyy"));
		
		System.out.println(sdfc1.parse("07/07/1997"));
		System.out.println(sdfc2.parse("07.07.1997"));
		
		System.out.println(sdfc2.parse("07.07.1997").getTime());
	}

}
