package entity;

/**
 * Our ChanceCard class.
 */
class ChanceCard {
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
     * The constructor for the ChanceCards
     */
    public String getDescription() {
        return description;
    }

    public int getEffect() {
        return effectnumber;
    }

}
