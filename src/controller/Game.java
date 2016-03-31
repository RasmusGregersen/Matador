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
//		Connector con = new Connector();
//		
//		ResultSet rs = con.doQuery("select * from Player");
//		
//		while(rs.next()){
//	         //Retrieve by column name
//	         String name  = rs.getString("Name");
//	         int id = rs.getInt("PlayerID");
//	         //Display values
//	         System.out.println("Name: " + name + "ID: " + id);
//
//	      }
		
		Gameboard.UpdateGUI();
		Rules.SetupGame();

		while (Rules.getWin() == false) {
			Rules.Turn();
		}
		
	}

}
