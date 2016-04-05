package controller;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import desktop_resources.GUI;
import entity.Gameboard;
import entity.Rules;
import mysql.*;
import mysql.Connector;

public class Game {

	public static void main(String[] args) throws SQLException {
		
		Gameboard.UpdateGUI();
		Rules.SetupGame();

		while (Rules.getWin() == false) {
			if (Rules.getPlayer(0) != null)
				Rules.Turn(Rules.getPlayer(0));
			if (Rules.getPlayer(1) != null)
				Rules.Turn(Rules.getPlayer(1));
			if (Rules.getPlayer(2) != null)
				Rules.Turn(Rules.getPlayer(2));
			if (Rules.getPlayer(3) != null)
				Rules.Turn(Rules.getPlayer(3));
			if (Rules.getPlayer(4) != null)
				Rules.Turn(Rules.getPlayer(4));
			if (Rules.getPlayer(5) != null)
				Rules.Turn(Rules.getPlayer(5));
		}
		
	}

}
