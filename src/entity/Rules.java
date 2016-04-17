package entity;

import java.awt.Color;
import java.lang.reflect.Array;
import entity.Gameboard;
import desktop_codebehind.Car;
import desktop_resources.GUI;

public class Rules {

	private static Car[] cars = new Car[6];
	private static Player[] players = new Player[6];
	private static int playerCount;

	public static Player getPlayer(int playernumber) { // getter for the array
		return players[playernumber];
	}

	public static int getPlayers() {
		return playerCount;
	}

	// Turn Method

	public static void Turn(Player player) {
		SaveGame();
		GUI.getUserButtonPressed("It's " + player.getName() + "'s turn!", "Roll");
		GUI.removeAllCars(player.getName());
		Rules.rollDice();
		GUI.setDice(Rules.getDie1(), Rules.getDie2());
		player.setFieldPos(Rules.getDiceSum());
		GUI.setCar(player.getFieldPos(), player.getName());
		Gameboard.setField(player.getFieldPos(), player);
		GUI.setBalance(player.getName(), player.getBalance());
		CheckLoseCondition(player);
		Rules.ExtraTurn(player);
	}

	public static void ExtraTurn (Player player) {
		if (Rules.getDie1() == Rules.getDie2()) {
			if (player.getExtraTurns() == 2) {
				// Go to Jail metode. 
			}
			else {
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
	public static void CheckWinConditions() {
		// Skriv winconditions ind i if statement, og sæt win = true
	}	

	// Lose Condition
	public static void CheckLoseCondition(Player player) {
		if (player.getBalance() == 0) {
			GUI.showMessage(player.getName() + " has gone bankrupt");
			playerCount = playerCount - 1;
			GUI.removeAllCars(player.getName());
			player = null;
		}
	}


	// Game Setup

	public static void SetupGame() {
		if (GUI.getUserLeftButtonPressed("Do you want to start a new game or load an existing game?", "New Game", "Load Game")) 
		{
			playerCount = GUI.getUserInteger("How many players do you wish to play", 2 , 6);	
			CarBuilder(playerCount);

			// Name Check	
			for (int i=0; i < playerCount; i++) {
				Player tmp = new Player("");
				EnterName:	
					while (true) {
						String name = GUI.getUserString("Please enter the name of player" + (i+1)).trim();
						if (name.length() < 1 || name.length() > 15 || name.indexOf(" ") == 0){
							GUI.showMessage("Invalid name!");
							continue;
						}

						for(Player p : players){
							if(p != null && p.getName().equals(name)) {
								GUI.showMessage("Invalid name!");
								continue EnterName;
							}
						}

						GUI.addPlayer(name, tmp.getBalance(), cars[i]);
						tmp.setName(name);
						break;

					}
				players[i] = tmp;
				// Mangler at tage højde for at de skal have forskellige navne + biler er tilfældige.
			}
		}
		else {
			LoadGame();
		}
	}

	public static void SaveGame() {
		for (int i=0; i<6; i++) {
			if (players[i] != null) {
				//Gem Player variabler i DB 
			}
		}
	}
	
	public static void LoadGame() {
		for (int i=0; i<6; i++) {
			if (players[i] != null) {
				//Indlæs Player variabler fra DB
			}
		}
	}

	// Car Builder

	public static void CarBuilder(int playerCount) {
		cars[0] = new Car.Builder()
				.primaryColor(Color.BLUE)
				.secondaryColor(Color.BLUE)
				.typeCar()
				.patternFill()
				.build();
		cars[1] = new Car.Builder() // chaining
				.primaryColor(Color.GREEN)
				.secondaryColor(Color.GREEN)
				.typeCar()
				.patternFill()
				.build();
		if (playerCount > 2) {
			cars[2] = new Car.Builder() // chaining
					.primaryColor(Color.ORANGE)
					.secondaryColor(Color.ORANGE)
					.typeCar()
					.patternFill()
					.build();
			if (playerCount > 3) {
				cars[3] = new Car.Builder() // chaining
						.primaryColor(Color.YELLOW)
						.secondaryColor(Color.YELLOW)
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
								.secondaryColor(Color.CYAN)
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