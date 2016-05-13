package fields;

import entity.Player;

public abstract class Field {

    private final String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void landOnField(Player player);

}