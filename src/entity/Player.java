
/**
 * This is the player class where player constructor and player information is located.
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
     * The player constructor is used in the Rules class where numbers between 2-6 player constructors are selected
     *
     *@param name The name of the actual player
     *@param balance The balance of the actual player
     *@param TotalAssets Value of all house, hotel and properties. Used for specific chancecard
     *@param FieldPos Players position on the gameboard
     *@param breweries Number of breweries
     *@param shipping Number of owned shipping properties
     *@param jailcard Number of jailcard in player possession
     *@param Jailturns Number of many times player has been in jail
     *@param jailed Jailed boolean which it set either true or false. False as standard
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
     * Returns numbers of Jailturns
     *@return Jailturns
     *
     *
     * */
    public int getJailturns() {
        return Jailturns;
    }
    /**
     * Setter for jailturn
     *@param jailturns Setter for jailturn
     *
     * */
    public void setJailturns(int jailturns) {
        Jailturns = jailturns;
    }
    /**
     * Getters and setters to seek information about the constructed players
     * Returns if player is jailed
     *@return IsJailed Returns if player is jailed
     *
     * */
    public boolean isJailed() {
        return jailed;
    }

    /**
     * Setter with special condition
     * If jailed is true, the program moves car to jail position
     * If jailed is false, jailturns which is a counter is set to 0
     * @param jailed Boolean if player is jailed or not.
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

    /**
     *  Getters and setters for extra turn
     * @return getExtraTurns return number of extra turns
     * */

    public int getExtraTurns() {
        return ExtraTurns;
    }

    /**
     *   setter for extra turn
     *   @param i numbers extra turn
     *
     * */
    public void setExtraTurns(int i) {
        ExtraTurns = i;
    }

    /**
     *Return specific player balance
     * @return getBalance Return specific player balance
     *
     *
     * */
    public int getBalance() {
        return balance;
    }

    /**
     *
     * Method to withdraw from the balance
     *
     * @param i the value that subtracts the balance.
     *
     * */
    public void withdrawBalance(int i) {
        balance = balance - i;
        GUI.setBalance(name, balance);
    }

    /**
     *
     * Method to deposit from the balance
     * @param i the value which is adding up
     *
     * */
    public void depositBalance(int i) {
        balance = balance + i;
        GUI.setBalance(name, balance);
    }

    /**
     *
     * name to return player name
     * @return .
     *
     *
     * */
    public String getName() {
        return name;
    }
    /**
     *   Name setter
     *@param name name for the player
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *Sets number of shipping properties
     *@return getShipping
     *
     * */
    public int getShipping() {
        return shipping;
    }
    /**
     *   Shipping setter
     *
     * */
    public void setShipping() {
        shipping++;
    }
    /**
     * Return number of breweries
     *@return  breweries
     * */
    public int getBreweries() {
        return breweries;
    }
    /**
     * Set number of breweries
     *
     * */
    public void setBreweries() {
        breweries++;
    }
    /**
     * Return Field position
     *@return  FieldPos
     * */
    public int getFieldPos() {
        return FieldPos;
    }

    /**
     * Return Field position
     *@return  jailcard Return Field position
     * */
    public int getJailcard() {
        return jailcard;
    }
    /**
     *Setter for jail card
     *@param jailcard number of jailcards
     *
     * */
    public void setJailcard(int jailcard) {
        this.jailcard = jailcard;
    }

    /**
     *
     * Setter for field position with check for start field
     * Checks if player has passed the start position
     * FieldPos = The fieldid which is used to set the field position
     * @param FieldPos The fieldid which is used to set the field position
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
     * @param FieldPos The fieldID which is used to caculated to move the player on the board
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
     * Getters for TotalAssets
     *  Return totalAssets amount
     * @return getTotalAssets Return totalAssets amount
     *
     * */

    public int getTotalAssets() {
        return TotalAssets;
    }
    /**
     * Setter is int value added to TotalAsset
     * @param TotalAssets int value added to TotalAsset
     * */
    public void setTotalAssets(int TotalAssets) {
        this.TotalAssets = this.TotalAssets + TotalAssets;
    }

}