package entity;

/**
 * Our ChanceCard class.
 */

class ChanceCard {
    /**
     * Variables which contains the Message of the ChanceCard and a number with it's effect.
     */
    private final String description;
    private final int effectnumber;

    /**
     * The constructor for the ChanceCards
     */
    public ChanceCard(String description, int effectnumber) {
        this.description = description;
        this.effectnumber = effectnumber;
    }

    /**
     * Getters for both variables
     */
    public String getDescription() {
        return description;
    }
    public int getEffect() {
        return effectnumber;
    }

}
