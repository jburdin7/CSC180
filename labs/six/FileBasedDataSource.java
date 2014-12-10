

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//use a list to store the each line of the file temporarly, write back the file afterwards

public class FileBasedDataSource implements DataSource {
	private BufferedReader reader;
	private BufferedWriter writer;
	private Queue auctions = new LinkedList<String>();
	private int externalId = 0;
	public FileBasedDataSource() {
		try {
			writer = new BufferedWriter(new FileWriter("auction.dat",true));
			BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")));
			
			while(tempBuf.readLine() != null) {
				externalId++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine() {
		String str = null;
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			str = tempBuf.readLine();
		} catch (IOException e) {
			
		}
		return str;
	}
	
	public boolean findId(int id) {
		Pattern auction = Pattern.compile("(.{4})");
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			String str = "";
			while((str = tempBuf.readLine()) != null) {
				Matcher matcher = auction.matcher(str);
				matcher.find();
				int i = Integer.parseInt(matcher.group(1).trim());
				if(i ==id) {
					return true;
				}
			}
		} catch (IOException e) {
			
		}
		return false;
	}
	
	public void writeAuction(Auction auction) throws IOException {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy");
			
			writer.write(Padder.pad(externalId++, 2) + Padder.pad(auction.getName(), 25) +
					Padder.pad(auction.getDescription(), 15) + Padder.pad(auction.getId(), 15) +
					Padder.pad(auction.getCurrentBid(), 10) + Padder.pad(auction.getCreator(), 10) +
					Padder.pad(auction.getOwner(), 10) +
					Padder.pad(sdf.format(auction.getEndsBy()), 16) + Padder.pad(auction.getHasBid(), 5)
					+ "\r\n");
			writer.flush();
		}
		catch(Exception e) {
			
		}
	}

	@Override
	public List<Auction> getAuctions() {
		ArrayList<Auction> arryList = new ArrayList<Auction>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy");
		String auctionString = "";
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			while((auctionString = tempBuf.readLine()) != null) {

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
					Date date = sdf.parse(matcher.group(8).trim());
					boolean hasBid = Boolean.parseBoolean(matcher.group(9).trim());
					
					Auction auction = new Auction(exId, id, bid, description, name, creator, owner, date, hasBid);
					arryList.add(auction);
				}
			}
		} catch (ParseException e) {
			
		} catch (NumberFormatException e) {
			
		} catch (IOException e) {
			
		}
		return arryList;
	}

	@Override
	public Auction create(Auction auction) throws IOException {
		writeAuction(auction);
		return auction;
	}

	@Override
	public Auction retrieve(Long id) {
		Pattern auction = Pattern.compile("(.{2})");
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 7);
			String str = "";
			while((str = tempBuf.readLine()) != null) {
				Matcher matcher = auction.matcher(str);
				matcher.find();
				int i = Integer.parseInt(matcher.group(1).trim());
				if(i ==id) {
					Pattern auctionPattern = Pattern.compile("(.{2})(.{25})(.{15})(.{15})(.{10})(.{10})(.{10})(.{16})(.{5})");
					matcher = auctionPattern.matcher(str);
					
					if(matcher.find()) {
						Long exId = Long.parseLong(matcher.group(1).trim());
						String name = matcher.group(2).trim();
						String description = matcher.group(3).trim();
						Long newId = Long.parseLong(matcher.group(4).trim());
						Double bid = Double.parseDouble(matcher.group(5).trim());
						String creator = matcher.group(6).trim();
						String owner = matcher.group(7).trim();
						Date date = cal.getTime();
						boolean hasBid = Boolean.parseBoolean(matcher.group(9).trim());
						Auction auc = new Auction(exId, newId, bid, description, name, creator, owner, date, hasBid);
						return auc;
					}
				}
			}
		} catch (IOException e) {
			
		}
		return null;
	}

	@Override
	public void delete(Long id) throws IOException {
		Pattern a = Pattern.compile("(.{2})");
		String s = "";
		
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			String str = tempBuf.readLine();
			while(str != null) {
				Matcher matcher = a.matcher(str);
				matcher.find();
				
				if(str.isEmpty() == false) {
					int i = Integer.parseInt(matcher.group(1).trim());
					if(i == id) {
						s += externalId + " $$tombstone$$\r\n";
					}
					else {
						s += str + "\r\n";
					}
				}
				str = tempBuf.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter writer = new FileWriter("auction.dat");
		BufferedWriter stuff = new BufferedWriter(writer);
		PrintWriter output = new PrintWriter(stuff, false);
		output.println(s);
		output.flush();
	}

	@Override
	public void update(Auction auction, Long id) throws IOException {
		Pattern a = Pattern.compile("(.{2})");
		String s = "";
		
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			String str = tempBuf.readLine();
			while(str != null) {
				Matcher matcher = a.matcher(str);
				matcher.find();
				
				if(str.isEmpty() == false) {
					int i = Integer.parseInt(matcher.group(1).trim());
					if(i == id) {
						s += (Padder.pad(externalId++, 2) + Padder.pad(auction.getName(), 25) +
								Padder.pad(auction.getDescription(), 15) + Padder.pad(auction.getId(), 15) +
								Padder.pad(auction.getCurrentBid(), 10) + Padder.pad(auction.getCreator(), 10) +
								Padder.pad(auction.getOwner(), 10) +
								Padder.pad(auction.getEndsBy(), 16) + Padder.pad(auction.getHasBid(), 5)
								);
					}
					else {
						s += str;
					}
				}
				str = tempBuf.readLine();
				if(str != null) {
					s += "\r\n";
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter writer = new FileWriter("auction.dat");
		BufferedWriter stuff = new BufferedWriter(writer);
		PrintWriter output = new PrintWriter(stuff, false);
		output.println(s);
		output.flush();
	}

	public Long countIDs() {
		int count = 0;
		String auctionString = "";
		try(BufferedReader tempBuf = new BufferedReader(new InputStreamReader(new FileInputStream("auction.dat")))) {
			while((auctionString = tempBuf.readLine()) != null) {

				Pattern auctionPattern = Pattern.compile("(.{2})(.{25})(.{15})(.{15})(.{10})(.{10})(.{10})(.{16})(.{5})");
				Matcher matcher = auctionPattern.matcher(auctionString);
				
				if(matcher.find()) {
					count++;
				}
			}
		} catch (NumberFormatException e) {
			
		} catch (IOException e) {
			
		}
		return (long) count;
	}
}
