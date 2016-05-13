package fields;

import entity.Gameboard;
import entity.Player;

/**
 * Chance field class.
 */

public class Chance extends Field {

    /**
     * Chance field constructor with inherited name from superclass Field.
     * @param name this parameter for giving the field its name.
     */

    public Chance(String name) {
        super(name);
    }

    /**
     * Overrides the inherited LandOnField method so when you land on the field you will
     * draw a random card from the ChanceCardDeck.
     */

    @Override
    public void landOnField(Player player) {
        Gameboard.DrawCard(player);
    }
}
