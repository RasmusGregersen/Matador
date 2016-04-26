package fields;

import entity.Player;

public abstract class Field {
	
	private String name;
	
	public int getRent() {
		return 0;
	}
	
	public Field(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void landOnField(Player player);

	public int getHousePrice() {
		return 0;
	}
	
	public int getHouses() {
		return 0;
	}
	
	public Player getOwner() {
		return null;
	}
	
	public String getColor() {
		return "";
	}
	
	public int getPrice() {
		return 0;
	}

	public void addHouses(int houses) {
	}

	public void setHouses(int houses) {
	}

	
}