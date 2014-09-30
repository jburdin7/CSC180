import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;


public class CollectionUtils {

	public static void main(String[] args) {
		test();
	}
	
	public static int cardinality(Object obj, Collection coll) {
		int i = 0;
		if(coll.isEmpty() == false) {
			if(coll != null && obj != null) {
				for(Object o : coll) {
					if(o == obj) {
						i++;
					}
				}
				return i;
			}
			else{
				throw new NullPointerException();
			}
		}
		else{
			throw new IllegalStateException();
		}
	}
	
	public static void test() {
		Collection l = new ArrayList();
		Object o = new Object();
		
		try{
			System.out.println(cardinality(o, l));
		}
		catch(IllegalStateException e) {
			System.out.println("Test failed: empty collection");
		}
		
		l.add(o);
		
		try{
			System.out.println(cardinality(o, null));
		}
		catch(NullPointerException e) {
			System.out.println("Test failed: null collection");
		}
		
		try{
			System.out.println(cardinality(null, l));
		}
		catch(NullPointerException e) {
			System.out.println("Test failed: null object");
		}
		
		System.out.println(cardinality(o, l));
	}
}