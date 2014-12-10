

public class Padder {
	
	public static String pad(Object obj, int len) {
		//char c = 0x1e;
		StringBuilder strB;
		if(obj != null) {
			strB = new StringBuilder(obj.toString());
			if(obj.toString().length() < len) {
				for(int i = 0; i < len - obj.toString().length(); i++) {
					strB.append(" ");
				}
				return strB.toString();
			}
			else if(obj.toString().length() > len) {
				return obj.toString().substring(0, len);
			}
		}
		else {
			strB = new StringBuilder("");
			for(int i = 0; i < len; i++) {
				strB.append(" ");
			}
		}
		return strB.toString();
	}
	
	public static Object unpad(String str) {
		str = str.replace("0x1e", "");
		return str;
	}
	
	public static <T> T unpad(String str, Class<T> clazz) {
		T newThing;
		try{
			newThing = clazz.cast(str.trim());
		}
		catch(ClassCastException e) {
			throw new NumberFormatException();
		}
		return newThing;
	}
}
