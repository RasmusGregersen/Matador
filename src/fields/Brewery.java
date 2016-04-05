package fields;

import entity.Rules;

public class Brewery extends Ownable {

	public Brewery (String name, int price) {
		super(name, price);
	}

	@Override
	public int getRent() {	
		if (super.getOwner().getBreweries() == 2)
			return Rules.getDiceSum()*200;
		else
			return Rules.getDiceSum()*100;
	}
}
