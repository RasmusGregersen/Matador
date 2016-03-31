package fields;

import entity.Player;

public abstract class Ownable extends Field {
	private int price;
	private Player owner;
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	@Override
	public void landOnField(Player player) {
	}	

	public abstract int getRent(); // Abstract method declared, to be inherited by child classes.
	
	
}
