package fields;

public class Shipping extends Ownable {

	public Shipping(String name, int price) {
		super(name, price);
	}

	@Override
	public int getRent() {
		if (super.getOwner().getShipping() == 2)
			return 1000;
		else if (super.getOwner().getShipping() == 3)
			return 1500;
		else if (super.getOwner().getShipping() == 4)
			return 2000;
		else
			return 500;
	}
	
}
