
public class Padder {
	
	public String pad(Object obj, int len) {
		char c = 0x1e;
		StringBuilder strB = new StringBuilder((String) obj);
		if(obj.toString().length() < len) {
			for(int i = 0; i < len - obj.toString().length(); i++) {
				strB.append(c);
			}
			return strB.toString();
		}
		else if(obj.toString().length() > len) {
			return obj.toString().substring(0, len);
		}
		return (String) obj;
	}
	
	public Object unpad(String str) {
		str = str.replace("0x1e", "");
		return str;
	}
	
	public <T> T unpad(String str, Class<T> clazz) {
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
