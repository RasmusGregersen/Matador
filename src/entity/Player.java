package entity;

public class Player {
	private String name;
	private int balance = 30000;
	private int fleets = 0;
	private int FieldPos = 0;
	private int laborcamps = 0;
	
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
	
	public int getFleets() {
		return fleets;
	}
	public void setFleets() {
		fleets++;
	}

	public int getLaborcamps() {
		return laborcamps;
	}
	public void setLaborcamps() {
		laborcamps++;
	}
	public int getFieldPos() {
		return FieldPos;
	}
	public void setFieldPos(int FieldPos) {
		this.FieldPos = FieldPos + this.FieldPos;
		if (this.FieldPos > 21)
			this.FieldPos = this.FieldPos - 21;
	}
	
}
