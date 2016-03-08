package controller;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		Rules.rollDice();
		System.out.println(Rules.getDie1() + " " + Rules.getDie2() + " " + Rules.getDiceSum());
		
	}

}
