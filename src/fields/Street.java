package fields;

public class Street extends Ownable {
	
	private int baserent;
	
	public Street(String name, int price, int rent){
		super(name, price);
		baserent = rent;
	}

	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
