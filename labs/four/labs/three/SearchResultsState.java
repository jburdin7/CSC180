package labs.three;

import java.text.ParseException;
import java.util.Scanner;

public class SearchResultsState implements State {
	private Scanner buf;
	private AuctionService auction;
	private Auction[] auctions;
	private String searchString;
	private String user;
	
	public SearchResultsState(AuctionService auction, Scanner buf, String user) {
		this.buf = buf;
		this.auction = auction;
		this.user = user;
	}

	public void show() {
		System.out.println(user + ", what would you like to search for?");
		searchString = buf.nextLine();
		
		auctions =  auction.search(searchString);
		System.out.println(user + ", here are your search results:");
		System.out.println("------------------------------------Search Results------------------------------------");
		System.out.println(String.format("%-4s%-12s%-12s%-12s%-15s%s\n", "Id", "Name", "Description", "CurrentBid", "Owner", "EndsBy"));
		
		for(Auction auction : auctions) {
			System.out.println(auction.toString());
		}
		System.out.println("--------------------------------------------------------------------------------------\n");
	}

	public State next() {
		System.out.println("1 - Bid");
		System.out.println("2 - Search");
		System.out.println("3 - Edit");
		System.out.println("4 - Delete");
		System.out.println("5 - Return Home");
		
		String input = null;
		input = buf.nextLine();
		
		switch(input) {
			case"1": {
				try {
					System.out.println("What is the id of the item you want to bid on?");
					String bidItem = buf.nextLine();
					auction.bid(user, Long.parseLong(bidItem));
				} catch (ParseException e) {
					System.out.println("Invlaid id. Try again.");
				}
				return this;
			}
			case"2": {
				return new AuctionCreateState(auction, buf, user);	
			}
			case"3": {
				System.out.println("What is the id of the item you want to edit?");
				String id = buf.nextLine();
				Long i = Long.parseLong(id);
				if(auction.retreive(i).getCreator().equals(user)) {
					if(auction.retreive(i).getHasBid() == false) {
						Auction auc = auction.retreive(i);
						System.out.println("Enter the new name of the item");
						String name = buf.nextLine();
						System.out.println("Enter the new description of the item");
						String description = buf.nextLine();
						System.out.println("Enter the new date of the item");
						String date = buf.nextLine();
						System.out.println("Enter the new price of the item");
						String price = buf.nextLine();
						auc.setName(name);
						auc.setDescription(description);
						//auc.setCurrentBid(Integer.parseInt(price));
						auction.update(auc, i);
					}
					else {
						Auction auc = auction.retreive(i);
						System.out.println("Enter the new description of the item");
						String description = buf.nextLine();
						auc.setDescription(description);
						auction.update(auc, i);
					}
				}
				else {
					System.out.println("Only the creator can edit this item.\n");
				}
				return this;
			}
			case"4":
				System.out.println("What is the id of the item you want to delete?");
				String id;
				id = buf.nextLine();
				Long i = (long) 0;
				try {
					if(auction.retreive(i).getCreator().equals(user)) {
						if(auction.retreive(i).getHasBid() == false) {
							auction.delete(i);
						}
						else {
							System.out.println("Can not delete an item that has bids on it.");
						}
					}
					else {
						System.out.println("Only the creator can delete this item.");
					}
				}
				catch(ObjectNotFoundException e) {
					System.out.println("Could not find your item. Try again\n");
				}
				return this;
			case"5": {
				return new UserHomeState(auction, buf, user);
			}
			default: {
				System.out.println("I'm sorry, I couldn't process your request. Please try again.");
				return this;
			}
		}
	}

}
