package fields;

import entity.Rules;

/**
 * Brewery field class.
 */

public class Brewery extends Ownable {

    /**
     * Brewery constructor with inherited name and price from superclass Ownable.
     * @param name this parameter for giving the field its name.
     * @param price this parameter gives the brewery its price.
     */

    public Brewery(String name, int price) {
        super(name, price);
    }

    /**
     * Getter for the rent.
     * @return returns the rent depending on how many breweries that's owned and the dice sum.
     */

    @Override
    public int getRent() {
        if (super.getOwner().getBreweries() == 2)
            return Rules.getDiceSum() * 200;
        else
            return Rules.getDiceSum() * 100;
    }
}
