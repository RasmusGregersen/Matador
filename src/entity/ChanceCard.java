package entity;

public class ChanceCard {
	private String description;
	private int effectnumber;
	
	public ChanceCard(String description, int effectnumber) {
		this.description = description;
		this.effectnumber = effectnumber;
	}
	
	public String getDescription() {
		return description;
	}

	public int getEffect() {
		return effectnumber;
	}	

}
