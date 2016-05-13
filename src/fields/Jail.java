package fields;

import desktop_resources.GUI;
import entity.Player;
import entity.Rules;

public class Jail extends Field {

    public Jail(String name) {
        super(name);
    }

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
