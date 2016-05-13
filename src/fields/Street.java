package fields;

/**
 * Street field class.
 */

public class Street extends Ownable {

    private final int baserent;
    private final int houseprice;
    private final int houserent1;
    private final int houserent2;
    private final int houserent3;
    private final int houserent4;
    private final int hotelrent;
    private int houses;
    private final String color;

    public String getColor() {
        return color;
    }

    public int getHousePrice() {
        return houseprice;
    }

    /**
     * Street constructor. Name is inherited from Field and price is inherited from Ownable.
     * @param name this parameter is for giving the field its name.
     * @param price this parameter is for giving the field its price.
     * @param color this parameter gives the field its color.
     * @param baserent this parameter gives the fields its baserent.
     * @param houserent1 this parameter gives the field its rent with 1 house.
     * @param houserent2 this parameter gives the field its rent with 2 house.
     * @param houserent3 this parameter gives the field its rent with 3 house.
     * @param houserent4 this parameter gives the field its rent with 4 house.
     * @param hotelrent this parameter gives the field its rent with 1 hotel.
     * @param houseprice this parameter tells what the price of a house is on the field.
     */

    public Street(String name, String color, int price, int baserent, int houserent1, int houserent2, int houserent3, int houserent4, int hotelrent, int houseprice) {
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

    public int getHouses() {
        return houses;
    }

    /**
     * This method removes all houses from a field.
     */

    public void RemoveHouses() {
        houses = 0;
    }

    /**
     * This method adds houses to a field.
     */

    public void addHouses(int houses) {
        this.houses = this.houses + houses;
    }

    /**
     * Overrides the inherited LandOnField method so when you land on the field you will
     * pay rent depending on how many houses or field you own.
     * @return returns the rent depending on the above.
     *
     */

    @Override
    public int getRent() {
        if (houses == 1) {
            return houserent1;
        } else if (houses == 2) {
            return houserent2;
        } else if (houses == 3) {
            return houserent3;
        } else if (houses == 4) {
            return houserent4;
        } else if (houses == 5) {
            return hotelrent;
        } else
            return baserent;
    }
}
