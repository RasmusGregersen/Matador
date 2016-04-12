package fields;

public class Street extends Ownable {
	
	private int baserent;
	private int houseprice;
	private int houserent1;
	private int houserent2;
	private int houserent3;
	private int houserent4;
	private int hotelrent;
	

	
	public Street(String name, int price, int rent, int houserent1, int houserent2, int houserent3, int houserent4, int hotelrent, int houseprice){
		super(name, price);
		baserent = rent;
		houseprice = houseprice;
		houserent1 = houserent1;
		houserent2 = houserent2;
		houserent3 = houserent3;
		houserent4 = houserent4;
		hotelrent = hotelrent;
	}

	@Override
	public int getRent() {
		return baserent;
	}
	
	
}
