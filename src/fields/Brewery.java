package fields;

import entity.Rules;

public class Brewery extends Ownable {

	public Brewery (String name, int price) {
		super(name, price);
	}

	@Override
	public int getRent() {
		return Rules.getDiceSum()*100;
	}
}
