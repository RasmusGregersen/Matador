package fields;

import desktop_resources.GUI;
import entity.Player;

public class Jail extends Field {

	public Jail(String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player) {
		GUI.showMessage("Du er på besøg i fængsel");
	}
	
}
