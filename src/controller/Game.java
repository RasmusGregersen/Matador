package controller;

import entity.Gameboard;
import entity.PlayerOptions;
import entity.Rules;

/**
 * This is Group 2's Matador Game.
 * This class is the main controller which connects all our entities and boundaries
 *
 * @author  Group 2
 * @version 1.0
 * @since   13/05-2016
 */

class Game {
    /**
     * Our main method - Press run to start.
     */
    public static void main(String[] args) {
        /**
         * Creates the gameboard, and setups the rules for the game.
         */
        Gameboard.CreateGUI();
        Rules.SetupGame();

        /**
         * This is our game loop, that will run until someone wins the game.
         */
        while (!Rules.getWin()) {
            Rules.SaveGame();
            for (int i = 0; i < 6; i++) {
                if (Rules.getPlayer(i) != null) {
                    if (Rules.getPlayer(i).isJailed())
                        PlayerOptions.Jailturn(Rules.getPlayer(i));
                    else
                        Rules.Turn(Rules.getPlayer(i));
                }
            }
        }
    }
}