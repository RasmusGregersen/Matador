package fields;

import entity.ChanceDeck;
import entity.Player;

public class Chance extends Field {
	
	public Chance (String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player) {
		ChanceDeck.DrawCard(player);
	}
}
