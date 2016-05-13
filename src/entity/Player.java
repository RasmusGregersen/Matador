
/**
 * This is the player class where player constructor is located.
 * The player constructor is used in the Rules class where player is constructed
 *
 * @author Group 2
 * @version 1.0
 * @Since 13/5-2016
 *
 * */
package entity;

import desktop_resources.GUI;

/**
 * Private player variables
 * */

public class Player {
    private String name;
    private int balance;
    private int TotalAssets;
    private int FieldPos;
    private int breweries;
    private int shipping;
    private int jailcard;
    private int Jailturns;
    private boolean jailed;
    private int ExtraTurns;

    /**
     * Player constructor
     *
     *@Param name The name of the actual player
     *@Param balance The balance of the actual player
     *@Param TotalAssets Value of all house, hotel and properties. Used for specific chancecard
     *@Param FieldPos Players position on the gameboard
     *@Param breweries Number of breweries
     *@Param shipping Number of owned shipping properties
     *@Param jailcard Number of jailcard in player possession
     *@Param Jailturns Number of many times player has been in jail
     *@Param jailed Jailed boolean which it set either true or false. False as standard
     *
     * */

    public Player(String name, int balance, int TotalAssets, int FieldPos, int breweries, int shipping, int jailcard, int Jailturns, boolean jailed) {
        this.name = name;
        this.balance = balance;
        this.TotalAssets = TotalAssets;
        this.FieldPos = FieldPos;
        this.breweries = breweries;
        this.shipping = shipping;
        this.jailcard = jailcard;
        this.Jailturns = Jailturns;
        this.jailed = jailed;
    }

    /**
     * Getters and setters to seek information about the constructed players
     *
     * */

    public int getJailturns() {
        return Jailturns;
    }

    public void setJailturns(int jailturns) {
        Jailturns = jailturns;
    }

    public boolean isJailed() {
        return jailed;
    }

    /**
     * Setter with special condition
     * If jailed is true, the program moves car to jail position
     * If jailed is false, jailturns which is a counter is set to 0
     * */

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
        if (jailed) {
            GUI.removeAllCars(name);
            FieldPos = 11;
            GUI.setCar(FieldPos, name);
        }
        if (!jailed) {
            GUI.removeAllCars(name);
            FieldPos = 11;
            GUI.setCar(FieldPos, name);
            Jailturns = 0;
        }
    }

    /** Getters and setters for extra turn */

    public int getExtraTurns() {
        return ExtraTurns;
    }

    public void setExtraTurns(int i) {
        ExtraTurns = i;
    }


    public int getBalance() {
        return balance;
    }

    /**
     *
     * Method to withdraw from the balance
     *
     * */
    public void withdrawBalance(int i) {
        balance = balance - i;
        GUI.setBalance(name, balance);
    }

    /**
     *
     * Method to deposit from the balance
     *
     * */
    public void depositBalance(int i) {
        balance = balance + i;
        GUI.setBalance(name, balance);
    }

    /**
     *
     *  method to return player name.
     *
     * */
    public String getName() {
        return name;
    }

    /**
     *
     * Methods to set and get player information
     *
     * */

    public void setName(String name) {
        this.name = name;
    }
    public int getShipping() {
        return shipping;
    }

    public void setShipping() {
        shipping++;
    }

    public int getBreweries() {
        return breweries;
    }

    public void setBreweries() {
        breweries++;
    }

    public int getFieldPos() {
        return FieldPos;
    }


    public int getJailcard() {
        return jailcard;
    }

    public void setJailcard(int jailcard) {
        this.jailcard = jailcard;
    }

    /**
     *
     * Setter for field position with check for start field
     * Checks if player has passed the start position
     *
     * */

    public void setFieldPos(int FieldPos) {
        if (this.FieldPos > FieldPos) {
            GUI.showMessage("Du modtager hermed kr. 4000,- for at passere start");
            balance = balance + 4000;
            GUI.setBalance(name, balance);
        }
        this.FieldPos = FieldPos;
        GUI.removeAllCars(name);
        GUI.setCar(this.FieldPos, name);
    }

    /**
     *
     * Method to move player
     * Checks if player has passed the start position
     *
     * */

    public void moveToFieldPos(int FieldPos) {
        this.FieldPos = FieldPos + this.FieldPos;
        if (this.FieldPos > 40) {
            this.FieldPos = this.FieldPos - 40;
            GUI.showMessage(name + " modtager hermed kr. 4000,- for at passere start");
            balance = balance + 4000;
            GUI.setBalance(name, balance);
        }
        GUI.removeAllCars(name);
        GUI.setCar(this.FieldPos, name);
    }

    /**
     *
     * Getters and setters for TotalAssets
     * Setter is int value added to TotalAsset
     *
     * */

    public int getTotalAssets() {
        return TotalAssets;
    }

    public void setTotalAssets(int TotalAssets) {
        this.TotalAssets = this.TotalAssets + TotalAssets;
    }

}