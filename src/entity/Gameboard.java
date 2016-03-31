package entity;

import java.awt.Color;
import fields.*;
import fields.Ownable;
import desktop_fields.*;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Jail;
import desktop_fields.Shipping;
import desktop_fields.Tax;
import desktop_resources.GUI;
import desktop_resources.buildings.*;

public class Gameboard {
	private static fields.Field[] fieldValue = new fields.Field[21]; // Field array created
	private static desktop_fields.Field[] fieldGUI = new desktop_fields.Field[40];
	
	public fields.Field getField(int fieldID) { // getter for the array
		return fieldValue[fieldID];
		}
	
	public static void setField(int fieldID, Player player) { // method to trigger landOnField method.
		fieldValue[fieldID - 1].landOnField(player);
	}
	
	public Gameboard() // Constructor for GameBoard to fill Field array and create GUI.
	{
		UpdateGUI();
	}
	public void UpdateGUI()
	{
		
		fieldValue[0] = new fields.Start("Start");
		fieldGUI[0] = new Start.Builder()
				.setTitle(fieldValue[0].getName())
				.setDescription(fieldValue[0].getClass().getSimpleName())
				.setBgColor(Color.GREEN)
				.build();

		fieldValue[1] = new fields.Street("Rødovrevej", 1200, 50);
		fieldGUI[1] = new Street.Builder()
				.setTitle(fieldValue[1].getName())
				.setDescription(fieldValue[1].getClass().getSimpleName())
				.setSubText(""+((Ownable) fieldValue[1]).getPrice())
				.setRent(""+((Ownable) fieldValue[1]).getRent())
				.setBgColor(Color.BLUE)
				.build();
		

		GUI.create(fieldGUI);
		
	}
}
