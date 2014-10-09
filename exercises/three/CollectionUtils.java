package models;
import java.util.Collection;


public abstract class CollectionUtils implements Collection<Object> {
	
	public static int cardinality(Object obj, Collection coll) {
		int i = 0;
		for(Object o : coll) {
			if(o.equals(obj)) {
				i++;
			}
		}
		return i;
	}
}