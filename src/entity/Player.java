package entity;

import desktop_resources.GUI;

public class Player {
	private String name;
	private int balance;
	private int breweries;
	private int shipping;
	private int FieldPos;
	private int jailcard;
	private int ExtraTurns;
	private boolean jailed;
	private int Jailturns;
	private int TotalAssets;

	public Player(String name, int balance, int breweries, int shipping, int FieldPos, int jailcard, boolean jailed, int Jailturns, int TotalAssets){
		this.name = name;
        this.breweries = breweries;
        this.shipping = shipping;
        this.FieldPos = FieldPos;
        this.jailcard = jailcard;
        this.jailed = jailed;
        this.Jailturns = Jailturns;
        this.TotalAssets = TotalAssets;
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
	public int withdrawBalance(int i) {
		balance = balance - i;
		GUI.setBalance(name, balance);
		return balance;
	}
	// Method to deposit from the balance
	public int depositBalance(int i) {
		balance = balance + i;
		GUI.setBalance(name, balance);
		return balance;
	}
	// method to return player name.
	public String getName()	{
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
			GUI.showMessage("Du modtager hermed kr. 4000,- for at passere start");
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
