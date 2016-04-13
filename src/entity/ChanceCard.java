package entity;

public class ChanceCard {
	private String description;
	private int action;
	
	public ChanceCard(String description, int action) {
		this.description = description;
		this.action = action;
	}
	
	public String getDescription() {
		return description;
	}

	public int getAction() {
		return action;
	}	

}
