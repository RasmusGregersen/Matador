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

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}

	@Override
	public int getPrice() {
		return price;
	}

	public boolean isPawned() {
		return pawned;
	}

	public void setPawned(boolean pawned) {
		this.pawned = pawned;
	}

	@Override
	public void landOnField(Player player) {
		if (owner == null) { // Checks if field has no Owner.
			if (player.getBalance() < price) {
				GUI.displayChanceCard(player.getName() + ": Har ikke råd til at købe denne grund...");
			}
			else if (GUI.getUserLeftButtonPressed(player.getName() + ": This " + getClass().getSimpleName() + " har ikke nogen ejer. Er du interesseret i at købe den?", "Ja", "Nej")) 
			{
				player.withdrawBalance(price);
				player.setTotalAssets(price);
				owner=player;
				GUI.setOwner(player.getFieldPos(), player.getName());
				if (Gameboard.IsPropertyReady(player, Gameboard.getField(player.getFieldPos()).getColor()))
					Gameboard.getField(player.getFieldPos()).setHouses(0);
			}
		}
		else if (pawned) {


		}
		else if (owner.getBalance() == 0) { // Checks if the owner is bankrupt.
			if (player.getBalance() < price) {
				GUI.displayChanceCard(player.getName() + ": Har ikke råd til at købe denne grund...");
			}
			else if (GUI.getUserLeftButtonPressed(player.getName() + ": This " + getClass().getSimpleName() + " har ikke nogen ejer. Er du interesseret i at købe den?", "Ja", "Nej")) 
			{
				player.withdrawBalance(price);
				owner=player;
				GUI.setOwner(player.getFieldPos(), player.getName());
			}
		}
		else if (player == owner) { // Checks if the actual player is the owner.
			GUI.displayChanceCard(player.getName() + ": Welcome back!");
		}
		else { // Otherwise the field must be owned by another active player.
			GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + getRent());
			player.withdrawBalance(getRent());
			owner.depositBalance(getRent());
			GUI.setBalance(owner.getName(), owner.getBalance());
		}	
	}

	public abstract int getRent(); // Abstract method declared, to be inherited by child classes.


}
