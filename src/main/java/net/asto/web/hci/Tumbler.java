package net.asto.web.hci;

public class Tumbler {
	private String id;
	private String water;
	
	public Tumbler() {
		
	}
	
	public Tumbler(String water) {
		super();
		this.water = water;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}

	@Override
	public String toString() {
		return "Tumbler [id=" + id + ", water=" + water + "]";
	}
	
	
}
