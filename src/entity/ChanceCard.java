package entity;

//Chancecard class
class ChanceCard {
    private final String description;
    private final int effectnumber;

    //The constructor for the ChanceCards
    public ChanceCard(String description, int effectnumber) {
        this.description = description;
        this.effectnumber = effectnumber;
    }

    public String getDescription() {
        return description;
    }

    public int getEffect() {
        return effectnumber;
    }

}
