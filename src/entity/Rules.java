package entity;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import fields.Brewery;
import fields.Ownable;
import fields.Shipping;
import fields.Street;
import mysql.Connector;

import java.awt.*;
import java.lang.reflect.Array;
import java.sql.SQLException;

public class Rules {

    /**
     * Start Creates the connection.
     */

    private static Connector con = new Connector();

    /**
     * Creates an array with six slots for cars.
     */
    private static Car[] cars = new Car[6];

    /**
     * Creates an array with six slots for player.
     */

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

    /**
     * Turn which every player gets to. If they're not in jail.
     */

    public static void Turn(Player player) {
        if (!CheckWinConditions(player)) {
            PlayerOptions.Options(player);
            Rules.rollDice();
            player.moveToFieldPos(Rules.getDiceSum());
            Gameboard.setField(player.getFieldPos(), player);
            if (player.getBalance() < 0)
                PlayerOptions.Bankrupt(player);
            ExtraTurn(player);
        }
    }

    /**
     * Puts the player in jail.
     */

    public static void GoToJail(Player player) {
        GUI.removeAllCars(player.getName());
        player.setJailed(true);
    }

    /**
     * Gives the player an extra turn if the conditions are true.
     */

    public static void ExtraTurn(Player player) {
        if (Rules.getDie1() == Rules.getDie2()) {
            if (player.getExtraTurns() == 2) {
                GUI.showMessage(player.getName() + " har har kørt for stærkt og ryger derfor direkte i fængsel");
                Rules.GoToJail(player);
                player.setExtraTurns(0);
            } else if (!player.isJailed()) {
                player.setExtraTurns(player.getExtraTurns() + 1);
                Turn(player);
            }
        } else
            player.setExtraTurns(0);
    }


    private static boolean win = false;

    public static boolean getWin() {
        return win;
    }

    /**
     * Checks if the there is only one player left in the game.
     * If so it prints our a GUI.showMessage and tells the last player that he won.
     */

    public static boolean CheckWinConditions(Player player) {
        boolean out = false;
        if (playerCount == 1) {
            win = true;
            GUI.showMessage("Alle dine modstandere er slået ud og " + player.getName() + " har vundet spillet");
            out = true;
        }
        return out;
    }

    /**
     * Lose condition runs when a player presses surrender.
     * This methods removes the player from the array list.
     * Removes all his houses, and sets owner back to null and updates in on the GUI.
     */

    public static void LoseCondition(Player player) {
        GUI.showMessage(player.getName() + " har forladt spillet");
        playerCount = playerCount - 1;
        GUI.setBalance(player.getName(), 0);
        GUI.removeAllCars(player.getName());
        for (int i = 0; i < 6; i++) {
            if (players[i] != null && player == players[i]) {
                for (int f = 1; f < 41; f++) {
                    if (Gameboard.getField(f) instanceof Street || Gameboard.getField(f) instanceof Shipping || Gameboard.getField(f) instanceof Brewery) {
                        if (((Ownable) Gameboard.getField(f)).getOwner() == player) {
                            ((Ownable) Gameboard.getField(f)).setOwner(null);
                            GUI.removeOwner(f);
                        }
                        if (Gameboard.getField(f) instanceof Street) {
                            if (((Street) Gameboard.getField(f)).getHouses() != 0) {
                                ((Street) Gameboard.getField(f)).RemoveHouses();
                                PlayerOptions.HouseorHotel(f);
                            }
                        }
                    }
                }
                try {
                    con.updateField(i);
                    con.removePlayer(i);
                    players[i] = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Setup game is the method to setup the game.
     * It gives the actors the choice to enter their desired name.
     * It also gives the actors the choice of do they wanna load- or start a new game.
     * There after it gives the actor a choice of how many players he wish to be.
     */

    public static void SetupGame() {
        if (GUI.getUserLeftButtonPressed("Vil du starte et nyt spil eller indlæse et gammelt?", "Nyt Spil", "Indlæs Spil")) {
            if (!Connector.isOffline()) {
                con.setDBname(GUI.getUserSelection("Vælg en af de gemte spil at overskrive", "Matador1", "Matador2", "Matador3", "Matador4", "Matador5"));
                try {
                    con.ResetDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            playerCount = GUI.getUserInteger("Hvor mange spillere ønsker i at spille?", 2, 6);
            CarBuilder();

            /**
             * Name check within the setup game.
             * It checks for a valid name, and two players cannot have the same name.
             */

            for (int i = 0; i < playerCount; i++) {
                Player tmp = new Player("", 30000, 0, 1, 0, 0, 0, 0, false);
                EnterName:
                while (true) {
                    String name = GUI.getUserString("Indtast navnet på Player" + (i + 1)).trim();
                    if (name.length() < 1 || name.length() > 15 || name.indexOf(" ") == 0) {
                        GUI.showMessage("Uacceptabelt navn!");
                        continue;
                    }

                    for (Player p : players) {
                        if (p != null && p.getName().equals(name)) {
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
        } else {
            if (!Connector.isOffline())
                LoadGame();
            else {
                GUI.showMessage("Du kan ikke indlæse et spil, da du ikke har forbindelse til databasen!");
                System.exit(1);
            }
        }
    }

    /**
     * Method to save the game to the database.
     * If the person playing the game is online.
     */

    public static void SaveGame() {
        if (!Connector.isOffline()) {
            try {
                for (int i = 0; i < 6; i++) {
                    if (players[i] != null) {
                        con.updatePlayer(i);
                        con.updateField(i);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the game from the database.
     * Depending on which game you chose to load from.
     */

    private static void LoadGame() {
        CarBuilder();
        con.setDBname(GUI.getUserSelection("Vælg en af de gemte spil at indlæse", "Matador1", "Matador2", "Matador3", "Matador4", "Matador5"));
        try {
            for (int i = 0; i < 6; i++) {
                players[i] = con.getPlayer(i);
                con.getField(i);
                if (players[i] != null) {
                    playerCount = playerCount++;
                    GUI.addPlayer(players[i].getName(), players[i].getBalance(), cars[i]);
                    GUI.setCar(players[i].getFieldPos(), players[i].getName());
                }
            }
            for (int i = 1; i < 41; i++) {
                if (Gameboard.getField(i) instanceof Street || Gameboard.getField(i) instanceof Brewery || Gameboard.getField(i) instanceof Shipping) {
                    if (((Ownable) Gameboard.getField(i)).getOwner() != null) {
                        GUI.setOwner(i, ((Ownable) Gameboard.getField(i)).getOwner().getName());
                        if (((Ownable) Gameboard.getField(i)).isPawned()) {
                            GUI.setSubText(i, "Pantsat!");
                        }
                        if (Gameboard.getField(i) instanceof Street) {
                            PlayerOptions.HouseorHotel(i);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates six cars in the GUI. But doesn't place them before there
     * is a player who owns the car.
     */

    private static void CarBuilder() {
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
        cars[2] = new Car.Builder() // chaining
                .primaryColor(Color.ORANGE)
                .secondaryColor(Color.WHITE)
                .typeCar()
                .patternFill()
                .build();
        cars[3] = new Car.Builder() // chaining
                .primaryColor(Color.YELLOW)
                .secondaryColor(Color.WHITE)
                .typeCar()
                .patternFill()
                .build();
        cars[4] = new Car.Builder() // chaining
                .primaryColor(Color.WHITE)
                .secondaryColor(Color.WHITE)
                .typeCar()
                .patternFill()
                .build();
        cars[5] = new Car.Builder() // chaining
                .primaryColor(Color.CYAN)
                .secondaryColor(Color.WHITE)
                .typeCar()
                .patternFill()
                .build();
    }

    /**
     * Static roll dice method. Which rolls the dice
     * and returns the dice sum.
     */

    private static int[] dice = new int[2];
    public static void rollDice() {
        dice[0] = (int) Math.ceil(Math.random() * 6);
        dice[1] = (int) Math.ceil(Math.random() * 6);
        GUI.setDice(Rules.getDie1(), Rules.getDie2());
    }

    /**
     * Die one value
     * @return returns die value.
     */

    public static int getDie1() {
        return Array.getInt(dice, 0);
    }

    /**
     * Die two value
     * @return returns die value.
     */

    public static int getDie2() {
        return Array.getInt(dice, 1);
    }

    /**
     * Dice sum of the two die
     * @return returns dice sum.
     */

    public static int getDiceSum() {
        return dice[0] + dice[1];
    }

}