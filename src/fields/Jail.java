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
			GUI.showMessage("Du er på besøg i fængsel");
		else
		{
			GUI.showMessage("Du er sendt i fængsel!");
			Rules.GoToJail(player);
		}
	}

}
