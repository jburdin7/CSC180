package labs.three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReaderStuffs {
	private static void readLine() {
		File file = new File("bob.txt");
		try(BufferedReader buf = new BufferedReader(new FileReader(file))) {
			String line = buf.readLine();
			System.out.println(line);
		}
		catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
		readLine();
	}
}
