package fields;

import entity.Player;

public abstract class Field {
	
	private String name;
	
	public Field(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void landOnField(Player player);

	public int getHouses() {
		return 0;
	}
	
	public Player getOwner() {
		return null;
	}
	
	public String getColor() {
		return null;
	}
	
	public int getPrice() {
		return 0;
	}
}