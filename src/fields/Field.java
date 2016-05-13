package fields;

import entity.Player;

/**
 * Field class with it's abstract methods.
 */

public abstract class Field {

    private final String name;

    /**
     * Field constructor. Name is abstract and therefore given to all the subclasses.
     * @param name this parameter for giving the field its name.
     */

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void landOnField(Player player);

}