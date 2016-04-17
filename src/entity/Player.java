package entity;

import desktop_resources.GUI;

public class Player {
	private String name;
	private int balance = 30000;
	private int breweries = 0;
	private int shipping = 0;
	private int FieldPos = 0;
	private int jailcard = 0;
	private int ExtraTurns = 0;


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
		return balance;
	}
	// Method to deposit from the balance
	public int depositBalance(int i) {
		balance = balance + i;
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
		this.FieldPos = FieldPos + this.FieldPos;
		if (this.FieldPos > 40) {
			this.FieldPos = this.FieldPos - 40;
			GUI.displayChanceCard("Du modtager hermed kr. 4000,- for at passere start");
			balance = balance + 4000;
		}
	}
	
}
