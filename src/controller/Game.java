package controller;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import desktop_resources.GUI;
import entity.Gameboard;
import entity.PlayerOptions;
import entity.Rules;
import mysql.*;
import mysql.Connector;

public class Game {

	public static void main(String[] args) throws SQLException {

		Gameboard.UpdateGUI();
		Rules.SetupGame();

		while (Rules.getWin() == false) 
		{
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
