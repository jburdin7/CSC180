package labs.four;
import java.text.ParseException;



public interface Converter<T> {
	T parse(String fromString);
	String format(T fromT);
}
