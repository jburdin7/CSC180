package labs.three;

import java.util.Date;
import java.util.Map;

public class Auction {
	private final Long id;
	private Double currentBid;
	private Integer numberOfBidsRemaining;
	private String name;
	private String owner;
	private String creator;
	private String description;
	private long timeLeftInMillis;
	private long externalId;
	private Date endsBy;
	private boolean hasBid;
	private Map<String, Object> properties;
	
	public Auction(Long id, Double currentBid, String name, String creator, String description, Date endsBy, long externalId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.currentBid = currentBid;
		this.creator = creator;
		this.endsBy = endsBy;
		this.externalId = externalId;
	}
	
	public Auction(Long id, Double currentBid, 
			String name, String creator, String description,
			long timeLeftInMillis, long externalId) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.creator = creator;
		this.description = description;
		this.timeLeftInMillis = timeLeftInMillis;
		this.externalId = externalId;
	}
	
//	public Auction(Integer id, Integer currentBid, 
//			String name, String description, long timeLeftInMillis, 
//			Map<String, Object> properties) {
//		this.id = id;
//		this.currentBid = currentBid;
//		this.name = name;
//		this.description = description;
//		this.timeLeftInMillis = timeLeftInMillis;
//		this.properties = properties;
//	}
	
	public Long getId() {
		return id;
	}

	public Double getCurrentBid() {
		return this.currentBid;
	}

	public String getName() {
		return name;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getDescription() {
		return description;
	}
	
	public long getTimeInMilliSeconds() {
		return timeLeftInMillis;
	}
	
	public Date getEndsBy() {
		return endsBy;
	}
	
	public Integer getNumberOfBidsRemaining() {
		return numberOfBidsRemaining;
	}
	
	public boolean getHasBid() {
		return hasBid;
	}

	public void setHasBid(boolean hasBid) {
		this.hasBid = hasBid;
	}

	public Map<String, Object> getProperties(String propertyName) {
		return properties;
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
	
	public void setCurrentBid(Double currentBid) {
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
		return 0;
	}
	
	// this must change
	public String toString() {
		TableRowAuctionConverter trac = new TableRowAuctionConverter();
		return trac.format(this);
	}
}