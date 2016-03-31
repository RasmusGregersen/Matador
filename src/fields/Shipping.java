package fields;

public class Shipping extends Ownable {

	public Shipping(String name, int price) {
		super(name, price);
	}

	@Override
	public int getRent() {
		return 0;
	}
	
}
