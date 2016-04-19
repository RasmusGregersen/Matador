package fields;

public class Street extends Ownable {
	
	private int baserent;
	private int houseprice;
	private int houserent1;
	private int houserent2;
	private int houserent3;
	private int houserent4;
	private int hotelrent;
	private int houses;
	private String color;

	@Override
	public String getColor() {
		return color;
	}

	public int getHouseprice() {
		return houseprice;
	}
	
	public Street(String name, String color, int price, int baserent, int houserent1, int houserent2, int houserent3, int houserent4, int hotelrent, int houseprice){
		super(name, price);
		this.color = color;
		this.baserent = baserent;
		this.houseprice = houseprice;
		this.houserent1 = houserent1;
		this.houserent2 = houserent2;
		this.houserent3 = houserent3;
		this.houserent4 = houserent4;
		this.hotelrent = hotelrent;
	}

	@Override
	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = this.houses + houses;
	}

	@Override
	public int getRent() {
		if (houses == 0) {
			return baserent*2;
		}
		if (houses == 1) {
			return houserent1;
		}
		else if (houses == 2) {
			return houserent2;
		}
		else if (houses == 3) {
			return houserent3;
		}
		else if (houses == 4) {
			return houserent4;
		}
		else if (houses == 5) {
			return hotelrent;
		}
		else 
			return baserent;
	}
}
