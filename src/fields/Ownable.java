package fields;

import desktop_resources.GUI;
import entity.Gameboard;
import entity.Player;

public abstract class Ownable extends Field {
    private final int price;
    private Player owner;
    private boolean pawned = false;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Ownable(String name, int price) {
        super(name);
        this.price = price;
    }

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
        if (owner == null || pawned) {
            if (player.getBalance() < price) {
                GUI.displayChanceCard(player.getName() + ": Har ikke råd til at købe denne grund...");
            } else if (GUI.getUserLeftButtonPressed(player.getName() + " : denne grund er tilgængelig for salg. Er du interesseret i at købe den?", "Ja", "Nej")) {
                player.withdrawBalance(price);
                player.setTotalAssets(price);
                owner = player;
                pawned = false;
                GUI.setOwner(player.getFieldPos(), player.getName());
                if (player.getFieldPos() == 6 || player.getFieldPos() == 16 || player.getFieldPos() == 26 || player.getFieldPos() == 36)
                    player.setShipping();
                else if (player.getFieldPos() == 13 || player.getFieldPos() == 29)
                    player.setBreweries();
            }
        } else if (player == owner) { // Checks if the actual player is the owner.
            GUI.displayChanceCard(player.getName() + ": Velkommen tilbage!");
        } else if (Gameboard.getField(player.getFieldPos()) instanceof Street) {
            if (Gameboard.IsPropertyReady(owner, ((Street) Gameboard.getField(player.getFieldPos())).getColor()) && ((Street) Gameboard.getField(player.getFieldPos())).getHouses() == 0) {
                GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + (getRent() * 2));
                player.withdrawBalance((getRent() * 2));
                owner.depositBalance((getRent() * 2));
                GUI.setBalance(owner.getName(), owner.getBalance());
            } else {
                GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + getRent());
                player.withdrawBalance(getRent());
                owner.depositBalance(getRent());
                GUI.setBalance(owner.getName(), owner.getBalance());
            }
        } else {
            GUI.displayChanceCard(player.getName() + ": er landet på " + owner.getName() + "'s felt. Udlejen er " + getRent());
            player.withdrawBalance(getRent());
            owner.depositBalance(getRent());
            GUI.setBalance(owner.getName(), owner.getBalance());
        }
    }

    public abstract int getRent(); // Abstract method declared, to be inherited by child classes.


}
