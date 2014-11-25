package labs.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
