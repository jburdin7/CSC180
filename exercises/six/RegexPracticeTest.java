import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class RegexPracticeTest {

	@Test
	public void test() {
		System.out.println(RegexPractice.extractOperators("5+6*33^2.1=11.56"));
		
		System.out.println(RegexPractice.extractNumbers("5+6*33^2.1=11.56"));
		
		System.out.println(RegexPractice.extractEmails("Here is my email, j.burdin@gmail.com, almost forget my second email jboss@daboss.com"));
		
		System.out.println(RegexPractice.extractListBody("<li>pie</li>"));
	}

}
