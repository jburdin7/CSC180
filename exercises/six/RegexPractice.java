import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexPractice {
	
	private static List list;
	
	public static List<String> extractNumbers(String equation) {
		list = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("\\d+(\\.\\d{1,2})?");
		Matcher matcher = pattern.matcher(equation);
		
		while(matcher.find()) {
			String t = matcher.group();
			list.add(t);
		}
		return list;
	}
	
	public static List<String> extractOperators(String equation) {
		list = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("\\+|-|\\*|/|\\^|=");
		Matcher matcher = pattern.matcher(equation);
		
		while(matcher.find()) {
			String t = matcher.group();
			list.add(t);
		}
		
		return list;
	}
	
	public static List<String> extractEmails(String email) {
		list = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
		Matcher matcher = pattern.matcher(email);
		
		while(matcher.find()) {
			String t = matcher.group();
			list.add(t);
		}
		return list;
	}
	
	public static String extractListBody(String payload) {
		String str = "";
		Pattern pattern = Pattern.compile("<li>(.+?)</li>");
		Matcher matcher = pattern.matcher(payload);
		
		if(matcher.find()) {
			str = matcher.group(1);
		}
		return str;
	}
}