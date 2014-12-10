

import java.io.IOException;
import java.text.ParseException;

public interface State {
	void show();
	State next() throws NumberFormatException, ParseException, IOException;
}
