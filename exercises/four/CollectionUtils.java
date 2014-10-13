package Assignment4;
import java.util.ArrayList;
import java.util.Collection;


public abstract class CollectionUtils implements Collection<Object> {
	private static Predicate predicate;
	
	public static int cardinality(Object obj, Collection coll) {
		int i = 0;
		for(Object o : coll) {
			if(o.equals(obj)) {
				i++;
			}
		}
		return i;
	}
	
	public static <T> Collection<T> filter(Collection<T> coll, Predicate<T> t) {
		Collection<T> resultingList = new ArrayList<T>();
		for(T element : coll) {
			if(t.evaluate(element) == true) {
				resultingList.add((T) element);
			}
		}
		return resultingList;
	}
}