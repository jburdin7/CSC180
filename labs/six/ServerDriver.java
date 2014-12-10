import java.io.IOException;

public class ServerDriver {
	public static void main(String[] args) {
		try {
			new Server().run();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Could not start server");
			e.printStackTrace();
		}
	}
}
