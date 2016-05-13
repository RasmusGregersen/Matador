package fields;

import desktop_resources.GUI;
import entity.Player;

public class Tax extends Field {

    private final int tax = 2000;

    public Tax(String name) {
        super(name);
    }

    @Override
    public void landOnField(Player player) {
        if (player.getFieldPos() == 5) {
            if (GUI.getUserLeftButtonPressed(player.getName() + ": Betal indkomstskat. Vælg at betale  " + tax * 2 + " eller 10% af din totale beholdning.", "Fastsat Afgift", "10%"))
                player.withdrawBalance(tax * 2);
            else
                player.withdrawBalance(Math.round(player.getBalance() / 10));
        } else if (player.getFieldPos() == 39) {
            GUI.showMessage("Ekstraordinær skat: " + player.getName() + " skal betale  på 2000");
            player.withdrawBalance(tax);
        }
    }
}
