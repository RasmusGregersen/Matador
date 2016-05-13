package fields;

/**
 * Shipping field class.
 */

public class Shipping extends Ownable {

    /**
     * Shipping constructor. Name is inherited from Field and price is inherited from Ownable.
     * @param name this parameter is for giving the field its name.
     * @param price this parameter is for giving the field its price .
     */

    public Shipping(String name, int price) {
        super(name, price);
    }

    /**
     * Overrides the inherited LandOnField method so when you land on the field you will
     * pay rent depending on how many shipping fields the owner has.
     * @return returns the rent depending of the numbers of shipping owned.
     *
     */

    @Override
    public int getRent() {
        if (super.getOwner().getShipping() == 2)
            return 1000;
        else if (super.getOwner().getShipping() == 3)
            return 2000;
        else if (super.getOwner().getShipping() == 4)
            return 4000;
        else
            return 500;
    }

}
