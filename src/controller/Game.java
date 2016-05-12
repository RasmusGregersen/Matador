package controller;

import entity.Gameboard;
import entity.PlayerOptions;
import entity.Rules;
import mysql.Connector;


public class Game {

	public static void main(String[] args) {
		//Creates the gameboard, and setups the rules for the game.
		Gameboard.CreateGUI();
		Rules.SetupGame();

		//This is the while loop, that will run until. someone has won the game.
		while (!Rules.getWin())
		{
			Rules.SaveGame();
			for (int i=0;i<6;i++)
			{
				if (Rules.getPlayer(i) != null)
				{
					if (Rules.getPlayer(i).isJailed())
						PlayerOptions.Jailturn(Rules.getPlayer(i));
					else
						Rules.Turn(Rules.getPlayer(i));
				}
			}
		}
	}
}