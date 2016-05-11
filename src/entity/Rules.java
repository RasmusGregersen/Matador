package entity;

import java.awt.Color;
import java.lang.reflect.Array;
import java.sql.SQLException;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import fields.Ownable;
import mysql.Connector;

public class Rules {
	private static Connector con = new Connector();
	private static Car[] cars = new Car[6];
	private static Player[] players = new Player[6];
	private static int playerCount = 0;

	public static Player getPlayer(int playernumber) { // getter for the array
		return players[playernumber];
	}

	public static int getPlayers() {
		return playerCount;
	}

	public static void setPlayer(int index, Player player) {
		players[index] = player;
	}

	// Turn Method

	public static void Turn(Player player) {
		if (!CheckWinConditions(player)) {
		PlayerOptions.Options(player);
			Rules.rollDice();
			player.moveToFieldPos(Rules.getDiceSum());
			Gameboard.setField(player.getFieldPos(), player);
		if (player.getBalance() <= 0)
			PlayerOptions.Bankrupt(player);
		}
	}

	public static void GoToJail (Player player) {
		GUI.removeAllCars(player.getName());
		player.setJailed(true);
	}


	public static void ExtraTurn (Player player) {
		if (Rules.getDie1() == Rules.getDie2()) {
			if (player.getExtraTurns() == 2) {
				GUI.showMessage(player.getName() + " har har kørt for stærkt og ryger derfor direkte i fængsel");
				Rules.GoToJail(player);
				player.setExtraTurns(0);
			}
			else if (!player.isJailed()) {
				player.setExtraTurns(player.getExtraTurns() + 1);
				Turn(player);
			}
		}
		else
			player.setExtraTurns(0);
	}

	// Win Conditions

	private static boolean win = false;
	public static boolean getWin() {
		return win;
	}
	public static boolean CheckWinConditions(Player player) {
		boolean out = false;
		if(playerCount == 1) {
			win = true;
			GUI.showMessage("Alle dine modstandere er slået ud og "+ player.getName() + " har vundet spillet");
			out = true;
		}
		return out;
	}	

	// Lose Condition
	public static void LoseCondition(Player player) {
		GUI.showMessage(player.getName() + " har forladt spillet");
		playerCount = playerCount - 1;
		GUI.setBalance(player.getName(), 0);
		GUI.removeAllCars(player.getName());

		for (int i = 0;i<6;i++) {
			if (player == players[i]) {
				try {
					for (int f = 1;f<41;i++) {
						if ( ((Ownable) Gameboard.getField(f)).getOwner() == player) {
							((Ownable) Gameboard.getField(f)).setOwner(null);
							GUI.removeOwner(f);
						}

					}
					con.updateField(i);
					con.removePlayer(i);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				players[i] = null;
			}

		}
	}

	// Game Setup

	public static void SetupGame() {
		if (GUI.getUserLeftButtonPressed("Vil du starte et nyt spil eller indlæse et gammelt?", "Nyt Spil", "Indlæs Spil")) 
		{
			con.setDBname(GUI.getUserSelection("Vælg en af de gemte spil at overskrive", "Matador1", "Matador2", "Matador3", "Matador4", "Matador5"));
			try {
				con.ResetDatabase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			playerCount = GUI.getUserInteger("Hvor mange spillere ønsker i at spille?", 2 , 6);	
			CarBuilder();

			// Name Check	
			for (int i=0; i < playerCount; i++) {
				Player tmp = new Player("",3000,0,1,0,0,0,0,false);
				EnterName:	
					while (true) {
						String name = GUI.getUserString("Indtast navnet på Player" + (i+1)).trim();
						if (name.length() < 1 || name.length() > 15 || name.indexOf(" ") == 0){
							GUI.showMessage("Uacceptabelt navn!");
							continue;
						}

						for(Player p : players){
							if(p != null && p.getName().equals(name)) {
								GUI.showMessage("Uacceptabelt navn!");
								continue EnterName;
							}
						}

						GUI.addPlayer(name, tmp.getBalance(), cars[i]);
						tmp.setName(name);
						break;

					}
				players[i] = tmp;
				GUI.setCar(1, players[i].getName());
				// Mangler at tage højde for at de skal have forskellige navne + biler er tilfældige.
			}
		}
		else {
			LoadGame();
		}
	}

	public static void SaveGame() {
		try {
			for (int i = 0; i < 6; i++) {
				if (players[i] != null) {
					con.updatePlayer(i);
					con.updateField(i);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void LoadGame() {
		con.setDBname(GUI.getUserSelection("Vælg en af de gemte spil at indlæse", "Matador1", "Matador2", "Matador3", "Matador4", "Matador5"));
		try {
			CarBuilder();
			for (int i=0; i<6; i++) {
				players[i] = con.getPlayer(i);
				con.updateField(i);
				if (players[i] instanceof Player) {
					playerCount = playerCount++;
					GUI.addPlayer(players[i].getName(), players[i].getBalance(), cars[i]);
					GUI.setCar(players[i].getFieldPos(), players[i].getName());
				}
			}
			Gameboard.CreateGUI();
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
	}

	// Car Builder

	public static void CarBuilder() {
		cars[0] = new Car.Builder()
				.primaryColor(Color.BLUE)
				.secondaryColor(Color.WHITE)
				.typeCar()
				.patternFill()
				.build();
		cars[1] = new Car.Builder() // chaining
				.primaryColor(Color.GREEN)
				.secondaryColor(Color.WHITE)
				.typeCar()
				.patternFill()
				.build();
		if (playerCount > 2) {
			cars[2] = new Car.Builder() // chaining
					.primaryColor(Color.ORANGE)
					.secondaryColor(Color.WHITE)
					.typeCar()
					.patternFill()
					.build();
			if (playerCount > 3) {
				cars[3] = new Car.Builder() // chaining
						.primaryColor(Color.YELLOW)
						.secondaryColor(Color.WHITE)
						.typeCar()
						.patternFill()
						.build();
				if (playerCount > 4) {
					cars[4] = new Car.Builder() // chaining
							.primaryColor(Color.WHITE)
							.secondaryColor(Color.WHITE)
							.typeCar()
							.patternFill()
							.build();
					if (playerCount > 5) {
						cars[5] = new Car.Builder() // chaining
								.primaryColor(Color.CYAN)
								.secondaryColor(Color.WHITE)
								.typeCar()
								.patternFill()
								.build();
					}
				}
			}
		}
	}

	// Dice

	private static int[] dice = new int [2];
	public static void rollDice() {
		dice[0] = (int) Math.ceil(Math.random()*6);
		dice[1] = (int) Math.ceil(Math.random()*6);
		GUI.setDice(Rules.getDie1(), Rules.getDie2());
	}
	public static int getDie1() {
		return Array.getInt(dice, 0);
	}
	public static int getDie2() {
		return Array.getInt(dice, 1);
	}
	public static int getDiceSum() {
		return dice[0]+dice[1];
	}

}