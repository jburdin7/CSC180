package Lab2;

import java.util.Map;
import java.util.Map.Entry;

public class Auction {
	private final Integer id;
	private Integer currentBid;
	private String name;
	private String owner;
	private String description;
	private Map<String, Object> properties;
	
	public Auction(Integer id, Integer currentBid, String name, String description) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
	}
	
	public Auction(Integer id, Integer currentBid, String name, String description, Map<String, Object> properties) {
		this.id = id;
		this.currentBid = currentBid;
		this.name = name;
		this.description = description;
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
	
	public String toString() {
		String str = "name: " + name + " owner: " + owner + " id=" + id + " currentBid: " + currentBid + "\n Properties: \n";
		if(this.properties != null) {
			for(Entry<String, Object> entry : properties.entrySet()) {
				str = str + entry.getKey() + "\n";
			}
		}
		return str;
	}
}
