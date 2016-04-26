package fields;

import entity.Gameboard;
import entity.Player;

public class Chance extends Field {
	
	public Chance (String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player) {
		Gameboard.DrawCard(player);
	}
}
