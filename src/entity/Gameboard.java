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
	private static fields.Field[] fieldValue = new fields.Field[40]; // Field array created
	private static desktop_fields.Field[] fieldGUI = new desktop_fields.Field[40];

	public static fields.Field getField(int fieldID) { // getter for the array
		return fieldValue[fieldID];
		}
	
	public static void setField(int fieldID, Player player) { // method to trigger landOnField method.
		fieldValue[fieldID - 1].landOnField(player);
	}
	
	public Gameboard() // Constructor for GameBoard to fill Field array and create GUI.
	{
		UpdateGUI();
	}
	public static void UpdateGUI()
	{

		fieldValue[0] = new fields.Start("START");
		fieldGUI[0] = new Start.Builder()
				.setTitle(fieldValue[0].getName())
				.setSubText("Modtag: 4000")
				.setDescription("Modtag kr. 4.000,- når de passerer start")
				.setFgColor(null)
				.setBgColor(Color.RED)
				.build();

		fieldValue[1] = new fields.Street("Rødovrevej", 1200, 50);
		fieldGUI[1] = new Street.Builder()
				.setTitle(fieldValue[1].getName())
				.setDescription(fieldValue[1].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[1]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[1]).getRent())
				.setFgColor(null)
				.setBgColor(Color.BLUE)
				.build();

		fieldValue[2] = new fields.Chance("Chancen1");
		fieldGUI[2] = new Chance.Builder()
				.build();	

		fieldValue[3] = new fields.Street("Hvidovrevej", 1200, 50);
		fieldGUI[3] = new Street.Builder()
				.setTitle(fieldValue[3].getName())
				.setDescription(fieldValue[3].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[3]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[3]).getRent())
				.setFgColor(null)
				.setBgColor(Color.BLUE)
				.build();	
		
		fieldValue[4] = new fields.Tax("SKAT");
		fieldGUI[4] = new Tax.Builder()
				.setTitle(fieldValue[4].getName())
				.setDescription("Betal indkomstskat 10% eller kr. 4.000,-")
				.setSubText("10% el. 4000")
				.setFgColor(null)
				.setBgColor(null)
				.build();	
		
		fieldValue[5] = new fields.Shipping("LB Færgerne", 4000);
		fieldGUI[5] = new Shipping.Builder()
				.setTitle(fieldValue[5].getName())
				.setDescription(fieldValue[5].getClass().getSimpleName())
				.setSubText("Pris: "+((Ownable) fieldValue[5]).getPrice())
				.setRent("Leje: 500 * [Antal rederier]")
				.setPicture(null)
				.setFgColor(null)
				.setBgColor(Color.GREEN)
				.build();

		fieldValue[6] = new fields.Street("Roskildevej", 2000, 100);
		fieldGUI[6] = new Street.Builder()
				.setTitle(fieldValue[6].getName())
				.setDescription(fieldValue[6].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[6]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[6]).getRent())
				.setFgColor(null)
				.setBgColor(Color.ORANGE)
				.build();	
		
		fieldValue[7] = new fields.Chance("Chancen2");
		fieldGUI[7] = new Chance.Builder()
				.build();	
		
		fieldValue[8] = new fields.Street("Valby Langgade", 2000, 100);
		fieldGUI[8] = new Street.Builder()
				.setTitle(fieldValue[8].getName())
				.setDescription(fieldValue[8].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[8]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[8]).getRent())
				.setFgColor(null)
				.setBgColor(Color.ORANGE)
				.build();
		
		fieldValue[9] = new fields.Street("Allégade", 2400, 150);
		fieldGUI[9] = new Street.Builder()
				.setTitle(fieldValue[9].getName())
				.setDescription(fieldValue[9].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[9]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[9]).getRent())
				.setFgColor(null)
				.setBgColor(Color.ORANGE)
				.build();
		
		fieldValue[10] = new fields.Jail("Fængsel");
		fieldGUI[10] = new Jail.Builder()
				.setTitle(fieldValue[10].getName())
				.setDescription("På besøg i fængslet")
				.setSubText(fieldValue[10].getName())
				.setPicture(null)
				.build();	
		
		fieldValue[11] = new fields.Street("Frederiksberg Allé", 2800, 200);
		fieldGUI[11] = new Street.Builder()
				.setTitle(fieldValue[11].getName())
				.setDescription(fieldValue[11].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[11]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[11]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();
		
		fieldValue[12] = new fields.Brewery("Carlsberg", 3000);
		fieldGUI[12] = new Brewery.Builder()
				.setTitle(fieldValue[12].getName())
				.setDescription(fieldValue[12].getName() + " bryggeri")
				.setSubText("Pris: "+((Ownable) fieldValue[12]).getPrice())
				.setRent("100 x [Terningslag]")
				.setPicture(null)
				.setFgColor(null)
				.setBgColor(null)
				.build();	
		
		fieldValue[13] = new fields.Street("Bülowsvej", 2800, 200);
		fieldGUI[13] = new Street.Builder()
				.setTitle(fieldValue[13].getName())
				.setDescription(fieldValue[13].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[13]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[13]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();
		
		fieldValue[14] = new fields.Street("Gammel Kongevej", 3200, 250);
		fieldGUI[14] = new Street.Builder()
				.setTitle(fieldValue[14].getName())
				.setDescription(fieldValue[14].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[14]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[14]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();
		
		fieldValue[15] = new fields.Shipping("Danmark", 4000);
		fieldGUI[15] = new Shipping.Builder()
				.setTitle(fieldValue[15].getName())
				.setDescription(fieldValue[15].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[15]).getPrice())
				.setRent("Leje: 500 * [Antal rederier]")
				.setPicture(null) // Billede kan filføjes
				.setFgColor(null)
				.setBgColor(Color.GREEN)
				.build();
		
		fieldValue[16] = new fields.Street("Bernstorfsvej", 3600, 300);
		fieldGUI[16] = new Street.Builder()
				.setTitle(fieldValue[16].getName())
				.setDescription(fieldValue[16].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[16]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[16]).getRent())
				.setFgColor(null)
				.setBgColor(Color.GRAY)
				.build();
		
		fieldValue[17] = new fields.Chance("Chancen3");
		fieldGUI[17] = new Chance.Builder()
				.build();	
		
		fieldValue[18] = new fields.Street("Hellerupvej", 3600, 300);
		fieldGUI[18] = new Street.Builder()
				.setTitle(fieldValue[18].getName())
				.setDescription(fieldValue[18].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[18]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[18]).getRent())
				.setFgColor(null)
				.setBgColor(Color.GRAY)
				.build();	
		
		fieldValue[19] = new fields.Street("Strandvej", 4000, 350);
		fieldGUI[19] = new Street.Builder()
				.setTitle(fieldValue[19].getName())
				.setDescription(fieldValue[19].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[19]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[19]).getRent())
				.setFgColor(null)
				.setBgColor(Color.GRAY)
				.build();	
		
		fieldValue[20] = new fields.Parking("Parkering");
		fieldGUI[20] = new Refuge.Builder()
			.setTitle(fieldValue[20].getName())
			.setDescription("Ta' en pause")
			.setSubText(fieldValue[20].getName())
			.setPicture(null)
			.setFgColor(null)
			.setBgColor(null)
			.build();
		
		fieldValue[21] = new fields.Street("Trianglen", 4400, 350);
		fieldGUI[21] = new Street.Builder()
				.setTitle(fieldValue[21].getName())
				.setDescription(fieldValue[21].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[21]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[21]).getRent())
				.setFgColor(null)
				.setBgColor(Color.RED)
				.build();	
		
		fieldValue[22] = new fields.Chance("Chancen4");
		fieldGUI[22] = new Chance.Builder()
				.build();
		
		fieldValue[23] = new fields.Street("Østerbrogade", 4400, 350);
		fieldGUI[23] = new Street.Builder()
				.setTitle(fieldValue[23].getName())
				.setDescription(fieldValue[23].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[23]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[23]).getRent())
				.setFgColor(null)
				.setBgColor(Color.RED)
				.build();	
		
		fieldValue[24] = new fields.Street("Østerbrogade", 4800, 400);
		fieldGUI[24] = new Street.Builder()
				.setTitle(fieldValue[24].getName())
				.setDescription(fieldValue[24].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[24]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[24]).getRent())
				.setFgColor(null)
				.setBgColor(Color.RED)
				.build();	
		
		fieldValue[25] = new fields.Shipping("Mols Linjen", 4000);
		fieldGUI[25] = new Shipping.Builder()
				.setTitle(fieldValue[25].getName())
				.setDescription(fieldValue[25].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[25]).getPrice())
				.setRent("Leje: 500 * [Antal rederier]")
				.setPicture(null)
				.setFgColor(null)
				.setBgColor(Color.GREEN)
				.build();
		
		fieldValue[26] = new fields.Street("Bredgade", 5200, 450);
		fieldGUI[26] = new Street.Builder()
				.setTitle(fieldValue[26].getName())
				.setDescription(fieldValue[26].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[26]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[26]).getRent())
				.setFgColor(null)
				.setBgColor(Color.WHITE)
				.build();	
		
		fieldValue[27] = new fields.Street("Kongens Nytorv", 5200, 450);
		fieldGUI[27] = new Street.Builder()
				.setTitle(fieldValue[27].getName())
				.setDescription(fieldValue[27].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[27]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[27]).getRent())
				.setFgColor(null)
				.setBgColor(Color.WHITE)
				.build();	
		
		fieldValue[28] = new fields.Brewery("Coca Cola", 3000);
		fieldGUI[28] = new Brewery.Builder()
				.setTitle(fieldValue[28].getName())
				.setDescription(fieldValue[28].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[28]).getPrice())
				.setRent("100 x [Terningslag]")
				.setPicture(null)
				.setFgColor(null)
				.setBgColor(null)
				.build();
		
		fieldValue[29] = new fields.Street("Østergade", 5600, 500);
		fieldGUI[29] = new Street.Builder()
				.setTitle(fieldValue[29].getName())
				.setDescription(fieldValue[29].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[29]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[29]).getRent())
				.setFgColor(null)
				.setBgColor(Color.WHITE)
				.build();	
		
		fieldValue[30] = new fields.GoToJail("Gå i fængsel");
		fieldGUI[30] = new Jail.Builder()
				.setTitle(fieldValue[30].getName())
				.setDescription("De fængsles! Slå to ens for at komme ud")
				.setSubText(fieldValue[30].getName())
				.setPicture(null)
				.build();
		
		fieldValue[31] = new fields.Street("Amagertorv", 6000, 550);
		fieldGUI[31] = new Street.Builder()
				.setTitle(fieldValue[31].getName())
				.setDescription(fieldValue[31].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[31]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[31]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();	
		
		fieldValue[32] = new fields.Street("Vimmelskaftet", 6000, 550);
		fieldGUI[32] = new Street.Builder()
				.setTitle(fieldValue[32].getName())
				.setDescription(fieldValue[32].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[32]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[32]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();
		
		fieldValue[33] = new fields.Chance("Chancen5");
		fieldGUI[33] = new Chance.Builder()
				.build();
		
		fieldValue[34] = new fields.Street("Nygade", 6400, 600);
		fieldGUI[34] = new Street.Builder()
				.setTitle(fieldValue[34].getName())
				.setDescription(fieldValue[34].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[34]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[34]).getRent())
				.setFgColor(null)
				.setBgColor(Color.YELLOW)
				.build();
		
		fieldValue[35] = new fields.Shipping("Skandinavisk Linietrafik A/S", 4000);
		fieldGUI[35] = new Shipping.Builder()
				.setTitle(fieldValue[35].getName())
				.setDescription(fieldValue[35].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[35]).getPrice())
				.setRent("Leje: 500 * [Antal rederier]")
				.setPicture(null) // Billede kan filføjes
				.setFgColor(null)
				.setBgColor(Color.GREEN)
				.build();
		
		fieldValue[36] = new fields.Chance("Chancen6");
		fieldGUI[36] = new Chance.Builder()
				.build();
		
		fieldValue[37] = new fields.Street("Frederiksberggade", 7000, 700);
		fieldGUI[37] = new Street.Builder()
				.setTitle(fieldValue[37].getName())
				.setDescription(fieldValue[37].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[37]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[37]).getRent())
				.setFgColor(null)
				.setBgColor(Color.CYAN)
				.build();
		
		fieldValue[38] = new fields.Tax("SKAT");
		fieldGUI[38] = new Tax.Builder()
				.setTitle(fieldValue[38].getName())
				.setDescription("Betal ekstraordinær statsskat kr. 2.000,-")
				.setSubText(fieldValue[38].getName())
				.setFgColor(null)
				.setBgColor(null)
				.build();	
		
		fieldValue[39] = new fields.Street("Rådhuspladsen", 8000, 1000);
		fieldGUI[39] = new Street.Builder()
				.setTitle(fieldValue[39].getName())
				.setDescription(fieldValue[39].getName())
				.setSubText("Pris: "+((Ownable) fieldValue[39]).getPrice())
				.setRent("Leje: "+((Ownable) fieldValue[39]).getRent())
				.setFgColor(null)
				.setBgColor(Color.CYAN)
				.build();
		
		
		GUI.create(fieldGUI);
	}
}
