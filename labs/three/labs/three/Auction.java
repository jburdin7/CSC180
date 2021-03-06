package labs.three;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Auction {
	private final Integer id;
	private Integer currentBid;
	private Integer numberOfBidsRemaining;
	private String name;
	private String owner;
	private String description;
	private long timeLeftInMillis;
	private Map<String, Object> properties;
	
	public Auction(Integer id, Integer currentBid, String name, String description, long timeLeftInMillis) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.timeLeftInMillis = timeLeftInMillis;
	}
	
	public Auction(Integer id, Integer currentBid, String name, String description, long timeLeftInMillis, Map<String, Object> properties) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.timeLeftInMillis = timeLeftInMillis;
		this.properties = properties;
	}
	
	public Integer getId() {
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
	
	public String getDescription() {
		return description;
	}
	
	public Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.timeLeftInMillis);
		return cal.getTime();
	}
	
	public Integer getNumberOfBidsRemaining() {
		return numberOfBidsRemaining;
	}
	
	public Map<String, Object> getProperties(String propertyName) {
		return this.properties;
	}
	
	public Object getProperty(String propertyName) {
		return properties.get(propertyName);
	}
	
	public <T> T getProperty(String propertyName, Class<T> propertyType) {
		return null;//need to fix
	}
	
	public void setName(String name) {
		if(this.owner == null) {
			this.name = name;
		}
	}
	
	public void setCurrentBid(Integer currentBid) {
		this.currentBid = currentBid;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTimeLeftInMillis(long timeLeftInMillis) {
		this.timeLeftInMillis = timeLeftInMillis;
	}

	public void setNumberOfBidsRemaining(Integer numberOfBidsRemaining) {
		this.numberOfBidsRemaining = numberOfBidsRemaining;
	}
	
	public void setProperty(String propertyName, Object propertyValue) {
		properties.put(propertyName, propertyValue);
	}
	
	public boolean equals(Object obj) {
		if(id == Integer.parseInt(obj.toString())) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	// this must change
	public String toString() {
		TableRowAuctionConverter trac = new TableRowAuctionConverter();
		return trac.format(this);
	}
}
