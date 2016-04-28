package fields;

import desktop_resources.GUI;
import entity.Gameboard;
import entity.Player;

public abstract class Ownable extends Field {
	private int price;
	private Player owner;
	private boolean pawned = false;

	@Override
	public Player getOwner() {
		return owner;
	}

	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}

	@Override
	public void addHouses(int houses) {
	}

	@Override
	public void setHouses(int houses) {
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getHousePrice() {
		return 0;
	}

	@Override
	public boolean isPawned() {
		return pawned;
	}
	
	@Override
	public void setPawned(boolean pawned) {
		this.pawned = pawned;
	}

	@Override
	public void landOnField(Player player) {
		if (owner == null || pawned==true) {
			if (player.getBalance() < price) {
				GUI.displayChanceCard(player.getName() + ": Har ikke råd til at købe denne grund...");
			}
			else if (GUI.getUserLeftButtonPressed(player.getName() + " : denne grund er tilgængelig for salg. Er du interesseret i at købe den?", "Ja", "Nej")) {
				player.withdrawBalance(price);
				player.setTotalAssets(price);
				owner=player;
				pawned=false;
				GUI.setOwner(player.getFieldPos(), player.getName());
				if (player.getFieldPos() == 6 || player.getFieldPos() == 16 || player.getFieldPos() == 26 || player.getFieldPos() == 36)
					player.setShipping();
				else if (player.getFieldPos() == 13 || player.getFieldPos() == 29)
					player.setBreweries();
			}
		}
		else if (player == owner) { // Checks if the actual player is the owner.
			GUI.displayChanceCard(player.getName() + ": Velkommen tilbage!");
		}
		else if (Gameboard.IsPropertyReady(owner, getColor())) {
				GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + (getRent()*2));
				player.withdrawBalance((getRent()*2));
				owner.depositBalance((getRent()*2));
				GUI.setBalance(owner.getName(), owner.getBalance());
		}
		else {
				GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + getRent());
				player.withdrawBalance(getRent());
				owner.depositBalance(getRent());
				GUI.setBalance(owner.getName(), owner.getBalance());
			}
	}

	public abstract int getRent(); // Abstract method declared, to be inherited by child classes.


}
