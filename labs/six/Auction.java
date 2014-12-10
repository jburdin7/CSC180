

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Auction implements Serializable{
	private final Long id;
	private Double currentBid = (double) 0;
	private Integer numberOfBidsRemaining = 0;
	private String name;
	private String owner;
	private String creator;
	private String description;
	private long timeLeftInMillis;
	private long externalId;
	private Date endsBy;
	private Date lastModifiedDate;
	private boolean hasBid;
	private Map<String, Object> properties;
	
	public Auction(Long id, Double currentBid, String name, String description, Date endsBy) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.currentBid = currentBid;
		this.timeLeftInMillis = endsBy.getTime();
		this.endsBy = endsBy;
	}
	
	public Auction(Long id, Double currentBid, 
			String name, String description, String creator,
			Integer bidsLeft, long endTimeMillis) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.numberOfBidsRemaining = bidsLeft;
		this.timeLeftInMillis = endTimeMillis;
		this.endsBy = new Date(endTimeMillis);
	}
	
	public Auction(Long id, Double currentBid, 
			String name, String description, String creator, Date endsBy,
			Integer bidsLeft) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.numberOfBidsRemaining = bidsLeft;
		this.endsBy = endsBy;
		this.timeLeftInMillis = endsBy.getTime();
	}
	
	public Auction(Long exId, Long id, Double currentBid, 
			String name, String description, String creator, String owner, Date endsBy,
			boolean hasBid) {
		this.externalId = exId;
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.owner = owner;
		this.endsBy = endsBy;
		this.timeLeftInMillis = endsBy.getTime();
	}
	
	public Auction(Long id, Double currentBid, 
			String name, String description, String creator, Date endsBy) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.endsBy = endsBy;
		this.timeLeftInMillis = endsBy.getTime();
	}
	
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
		return new Date(timeLeftInMillis);
	}
	
	public Integer getNumberOfBidsRemaining() {
		return numberOfBidsRemaining;
	}
	
	public boolean getHasBid() {
		return hasBid;
	}
	
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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