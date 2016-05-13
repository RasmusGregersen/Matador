package fields;

import desktop_resources.GUI;
import entity.Player;

/**
 * Tax field class.
 */

public class Tax extends Field {

    private final int tax = 2000;

    /**
     * Tax field constructor with inherited name from superclass Field.
     * @param name this parameter for giving the field its name.
     */

    public Tax(String name) {
        super(name);
    }

    /**
     * Overrides the inherited LandOnField method so when you land on the field you will
     * either pay two different tax depending on field you landed on.
     */

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
