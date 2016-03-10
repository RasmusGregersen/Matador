package entity;

import java.awt.Color;

import fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;

public class Gameboard {
	
	private static Field[] fieldValue = new Field[21]; // Field array created
	
	public Field getField(int fieldID) { // getter for the array
		return fieldValue[fieldID];
		}
	
	public static void setField(int fieldID, Player player) { // method to trigger landOnField method.
		fieldValue[fieldID - 1].landOnField(player);
	}
	
	public Gameboard() // Constructor for GameBoard to fill Field array and create GUI.
	{
		desktop_fields.Field[] fieldGUI = new desktop_fields.Field[40];
		
		for (int i = 0; i<40; i++) {
			
			
			
			if (type = Street) {
				
			
				
				
			}
			fieldValue[i] = new getType(FieldID = i+1)
			
		}
		
		
		
		
		fieldValue[0] = new Territory("Tribe Encampment", 100, 1000);
		fieldGUI[0] = new Street.Builder()
				.setTitle(fieldValue[0].getName())
				.setDescription(fieldValue[0].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[0]).getPrice())
				.setRent(""+((Ownable)fieldValue[0]).getRent())
				.setBgColor(Color.CYAN)
				
				.build();
		
		fieldValue[1] = new Fleet ("Second Sail", 4000);
		fieldGUI[1] = new Street.Builder()
				.setTitle(fieldValue[1].getName())
				.setDescription(fieldValue[1].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[1]).getPrice())
				.setRent(""+((Ownable)fieldValue[1]).getRent())
				.setBgColor(Color.BLUE)
				.build();
		
		fieldValue[2] = new Territory("Crater", 300, 1500);
		fieldGUI[2] = new Street.Builder()
				.setTitle(fieldValue[2].getName())
				.setDescription(fieldValue[2].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[2]).getPrice())
				.setRent(""+((Ownable)fieldValue[2]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[3] = new LaborCamp ("Huts in the mountain", 100, 2500);
		fieldGUI[3] = new Street.Builder()
				.setTitle(fieldValue[3].getName())
				.setDescription(fieldValue[3].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[3]).getPrice())
				.setRent(""+((LaborCamp)fieldValue[3]).getBaseRent())
				.setBgColor(Color.LIGHT_GRAY)
				.build();
		
		fieldValue[4] = new Territory("Mountain", 500, 2000);
		fieldGUI[4] = new Street.Builder()
				.setTitle(fieldValue[4].getName())
				.setDescription(fieldValue[4].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[4]).getPrice())
				.setRent(""+((Ownable)fieldValue[4]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[5] = new Refuge ("Walled City", 5000);
		fieldGUI[5] = new Street.Builder()
				.setTitle(fieldValue[5].getName())
				.setDescription(fieldValue[5].getClass().getSimpleName())
				.setSubText(""+((Refuge)fieldValue[5]).getBonus())
				.setBgColor(Color.GREEN)
				.build();
		
		fieldValue[6] = new Territory("Cold Desert", 700, 3000);
		fieldGUI[6] = new Street.Builder()
				.setTitle(fieldValue[6].getName())
				.setDescription(fieldValue[6].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[6]).getPrice())
				.setRent(""+((Ownable)fieldValue[6]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[7] = new Fleet ("Sea Grover", 4000);
		fieldGUI[7] = new Street.Builder()
				.setTitle(fieldValue[7].getName())
				.setDescription(fieldValue[7].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[7]).getPrice())
				.setRent(""+((Ownable)fieldValue[7]).getRent())
				.setBgColor(Color.BLUE)
				.build();
		
		fieldValue[8] = new Territory("Black Cave", 1000, 4000);
		fieldGUI[8] = new Street.Builder()
				.setTitle(fieldValue[8].getName())
				.setDescription(fieldValue[8].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[8]).getPrice())
				.setRent(""+((Ownable)fieldValue[8]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[9] = new Tax2 ("Goldmine", 2000);
		fieldGUI[9] = new Street.Builder()
				.setTitle(fieldValue[9].getName())
				.setDescription(fieldValue[9].getClass().getSimpleName())
				.setSubText(""+((Tax2)fieldValue[9]).getTax())
				.setBgColor(Color.RED)
				.build();
		
		fieldValue[10] = new Territory("The Werewall", 1300, 4300);
		fieldGUI[10] = new Street.Builder()
				.setTitle(fieldValue[10].getName())
				.setDescription(fieldValue[10].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[10]).getPrice())
				.setRent(""+((Ownable)fieldValue[10]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[11] = new LaborCamp ("The Pit", 100, 2500);
		fieldGUI[11] = new Street.Builder()
				.setTitle(fieldValue[11].getName())
				.setDescription(fieldValue[11].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[11]).getPrice())
				.setRent(""+((LaborCamp)fieldValue[11]).getBaseRent())
				.setBgColor(Color.LIGHT_GRAY)
				.build();
		
		fieldValue[12] = new Territory("Mountain Village", 1600, 4750);
		fieldGUI[12] = new Street.Builder()
				.setTitle(fieldValue[12].getName())
				.setDescription(fieldValue[12].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[12]).getPrice())
				.setRent(""+((Ownable)fieldValue[12]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[13] = new Fleet ("The Buccaneers", 4000);
		fieldGUI[13] = new Street.Builder()
				.setTitle(fieldValue[13].getName())
				.setDescription(fieldValue[13].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[13]).getPrice())
				.setRent(""+((Ownable)fieldValue[13]).getRent())
				.setBgColor(Color.BLUE)
				.build();
		
		fieldValue[14] = new Territory("South Citadel", 2000, 5000);
		fieldGUI[14] = new Street.Builder()
				.setTitle(fieldValue[14].getName())
				.setDescription(fieldValue[14].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[14]).getPrice())
				.setRent(""+((Ownable)fieldValue[14]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[15] = new Refuge ("Monastery", 500);
		fieldGUI[15] = new Street.Builder()
				.setTitle(fieldValue[15].getName())
				.setDescription(fieldValue[15].getClass().getSimpleName())
				.setSubText(""+((Refuge)fieldValue[15]).getBonus())
				.setBgColor(Color.GREEN)
				.build();
		
		fieldValue[16] = new Territory("Palace Gates", 2600, 5500);
		fieldGUI[16] = new Street.Builder()
				.setTitle(fieldValue[16].getName())
				.setDescription(fieldValue[16].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[16]).getPrice())
				.setRent(""+((Ownable)fieldValue[16]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[17] = new Tax ("Caravan", 4000);
		fieldGUI[17] = new Street.Builder()
				.setTitle(fieldValue[17].getName())
				.setDescription(fieldValue[17].getClass().getSimpleName())
				.setSubText(""+((Tax)fieldValue[17]).getTax())
				.setBgColor(Color.RED)
				.build();
		
		fieldValue[18] = new Territory("Tower", 3200, 6000);
		fieldGUI[18] = new Street.Builder()
				.setTitle(fieldValue[18].getName())
				.setDescription(fieldValue[18].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[18]).getPrice())
				.setRent(""+((Ownable)fieldValue[18]).getRent())
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[19] = new Fleet ("Private armade", 4000);
		fieldGUI[19] = new Street.Builder()
				.setTitle(fieldValue[19].getName())
				.setDescription(fieldValue[19].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[19]).getPrice())
				.setRent(""+((Ownable)fieldValue[19]).getRent())
				.setBgColor(Color.BLUE)
				.build();
		
		fieldValue[20] = new Territory("Castle", 4000, 8000);
		fieldGUI[20] = new Street.Builder()
				.setTitle(fieldValue[20].getName())
				.setDescription(fieldValue[20].getClass().getSimpleName())
				.setSubText(""+((Ownable)fieldValue[20]).getPrice())
				.setRent(""+((Ownable)fieldValue[20]).getRent())
				.setBgColor(Color.CYAN)
				.build();

		GUI.create(fieldGUI);
		
		
	}
}
