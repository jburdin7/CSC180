package labs.four;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//use a list to store the each line of the file temporarly, write back the file afterwards

public class FileBasedDataSource implements DataSource {
	private BufferedReader reader;
	BufferedWriter writer;
	private Queue auctions = new LinkedList<String>();
	private int externalId = 0;
	public FileBasedDataSource() {
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")));
			writer = new BufferedWriter(new FileWriter("auction.dat",true));
			reader.mark(26);
			while(reader.ready()) {
				reader.readLine();
				externalId++;
			}
			reader.reset();
		} catch (IOException e) {
			
		}
	}
	
	public static byte[] serialize(HashMap<Integer, Auction> hm) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos);) {
			oos.writeObject(hm);
		}
		return baos.toByteArray();
	}
	
	public static HashMap<Integer, Auction> deSerialize(byte[] person) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(person);
		try(ObjectInputStream ois = new ObjectInputStream(bais);) {
			return (HashMap<Integer, Auction>) ois.readObject();
		}
		catch (ClassNotFoundException e) {
			throw new IOException(e);
		}
	}
	
	public String readLine() throws IOException {
		String str = reader.readLine();
		return str;
	}
	
	public boolean findId(int id) throws IOException {
		Pattern auction = Pattern.compile("(\\d)\\s(\\w+)");
		while(reader.ready()) {
			String str = reader.readLine();
			Matcher matcher = auction.matcher(str);
			
			if(matcher.find() && matcher.group(1).equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public void writeAuction(Auction auction) throws IOException {
		try{	
			writer.write(Padder.pad(externalId++, 2) + Padder.pad(auction.getName(), 25) +
					Padder.pad(auction.getDescription(), 15) + Padder.pad(auction.getId(), 15) +
					Padder.pad(auction.getCurrentBid(), 10) + Padder.pad(auction.getCreator(), 10) +
					Padder.pad(auction.getOwner(), 10) +
					Padder.pad(auction.getEndsBy(), 16) + Padder.pad(auction.getHasBid(), 5)
					);
			writer.newLine();
			writer.flush();
		}
		catch(Exception e) {
			
		}
	}

	@Override
	public Auction[] getAuctions() throws IOException {
		ArrayList<Auction> arryList = new ArrayList<Auction>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		
		while(reader.ready()) {
			String auctionString = reader.readLine();
			Pattern auctionPattern = Pattern.compile("(.{2})(.{25})(.{15})(.{15})(.{10})(.{10})(.{10})(.{16})(.{5})");
			Matcher matcher = auctionPattern.matcher(auctionString);
			
			if(matcher.find()) {
				Long exId = Long.parseLong(matcher.group(1).trim());
				String name = matcher.group(2).trim();
				String description = matcher.group(3).trim();
				Long id = Long.parseLong(matcher.group(4).trim());
				Double bid = Double.parseDouble(matcher.group(5).trim());
				String creator = matcher.group(6).trim();
				String owner = matcher.group(7).trim();
				Date date = cal.getTime();
				boolean hasBid = Boolean.parseBoolean(matcher.group(9).trim());
				
				Auction auction = new Auction(exId, id, name, description, bid, creator, owner, date, hasBid);
				arryList.add(auction);
			}
			else {
				System.out.println("No match was found");
			}
		}
		return arryList.toArray(new Auction[arryList.size()]);
	}

	@Override
	public Auction create(Auction auction) throws IOException {
		writeAuction(auction);
		return null;
	}

	@Override
	public Auction retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction update(Auction auction, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
