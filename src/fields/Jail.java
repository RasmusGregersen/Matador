package fields;

import desktop_resources.GUI;
import entity.Player;
import entity.Rules;

/**
 * Jail field class.
 */

public class Jail extends Field {

    /**
     * Jail field constructor with inherited name from superclass Field.
     * @param name this parameter for giving the field its name.
     */

    public Jail(String name) {
        super(name);
    }

    /**
     * Overrides the inherited LandOnField method so when you land on the field you will
     * either go to jail or not.
     */

    @Override
    public void landOnField(Player player) {
        if (player.getFieldPos() == 11)
            GUI.showMessage(player.getName() + "  er på besøg i fængsel");
        else {
            GUI.showMessage(player.getName() + "  er sendt i fængsel!");
            Rules.GoToJail(player);
        }
    }

}
