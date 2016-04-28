package entity;

import desktop_resources.GUI;

public class Player {
	private String name;
	private int balance = 30000;
	private int breweries = 0;
	private int shipping = 0;
	private int FieldPos = 1;
	private int jailcard = 0;
	private int ExtraTurns = 0;
	private boolean jailed = false;
	private int Jailturns = 0;
	private int TotalAssets = 0;


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

	public Player(String name){
		this.name = name;
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
