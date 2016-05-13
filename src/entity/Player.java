package entity;

import desktop_resources.GUI;

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

    public int getJailturns() {
        return Jailturns;
    }

    public void setJailturns(int jailturns) {
        Jailturns = jailturns;
    }

    public boolean isJailed() {
        return jailed;
    }

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

    public int getExtraTurns() {
        return ExtraTurns;
    }

    public void setExtraTurns(int i) {
        ExtraTurns = i;
    }


    public int getBalance() {
        return balance;
    }

    // Method to withdraw from the balance
    public void withdrawBalance(int i) {
        balance = balance - i;
        GUI.setBalance(name, balance);
    }

    // Method to deposit from the balance
    public void depositBalance(int i) {
        balance = balance + i;
        GUI.setBalance(name, balance);
    }

    // method to return player name.
    public String getName() {
        return name;
    }

    // method to set player name.
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

    public int getTotalAssets() {
        return TotalAssets;
    }

    public void setTotalAssets(int totalAssets) {
        TotalAssets = totalAssets + TotalAssets;
    }

}