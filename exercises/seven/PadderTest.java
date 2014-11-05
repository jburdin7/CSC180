import org.junit.Test;

public class PadderTest {

	@Test
	public void test() {
		Padder padder = new Padder();
		System.out.println(padder.pad("bob", 3));
		System.out.println(padder.pad("bob", 2));
		System.out.println(padder.pad("bob", 4));
		
		
		System.out.println(padder.unpad("bob    ", Double.class));
		System.out.println(padder.unpad("123    ", Double.class));
	}

}
