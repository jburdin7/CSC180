package models;

public class Auction {
	private final String id;
	private final String name;
	private int currentBid;
	private String owner;
	
	public String getId() {
		return this.id;
	}

	public int getCurrentBid() {
		return this.currentBid;
	}

	public String getName() {
		return this.name;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public void setCurrentBid(int currentBid) {
		this.currentBid = currentBid;
	}
	
	public void setOwner(String owner) {
		if(this.owner == null) {
			this.owner = owner;
		}
	}
	
	public Auction(String id, int currentBid, String name) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(id == obj.toString()) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public String toString() {
		return "name: " + name + " owner: " + owner + " id=" + id + " currentBid: " + currentBid;
	}
}
