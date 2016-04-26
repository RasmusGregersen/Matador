package entity;

import desktop_resources.GUI;

public class PlayerOptions {

	public static void Jailturn(Player player) {
		if (player.getJailturns() == 3) {
			player.setJailed(false);
			GUI.showMessage(" " + player.getName() + "  har nu været i fængsel i tre turer, og bliver derfor løsladt, men  " + player.getName() + "  er tvunget til, at betale en bøde på 1000 kr,-");
			player.withdrawBalance(1000);
			Rules.Turn(player);
		}

		else if (player.getJailcard() > 0) {
			if (GUI.getUserLeftButtonPressed("Vil  " + player.getName() + "  bruge dit Chancekort til at komme ud af fængslet?", "Ja", "Nej")) {
				player.setJailcard(player.getJailcard()-1);
				player.setJailed(false);
			}
		}
		if (GUI.getUserLeftButtonPressed("Vil  " + player.getName() + "  betale en bøde på 1000 og komme ud af fængsel","Ja","Nej") && player.getBalance() > 1000 && player.isJailed()) {
			player.withdrawBalance(1000);
			player.setJailed(false);
		}
		else {
			GUI.showMessage(" " + player.getName() + "  har nu 3 forsøg til at slå dobbelt, og komme ud af fængsel");
			for (int i=0;i<3;i++) {
				GUI.getUserButtonPressed("It's " + player.getName() + "'s turn!", "Roll");
				Rules.rollDice();
				if (Rules.getDie1() == Rules.getDie2()) {
					GUI.showMessage(" " + player.getName() + "  slap ud!  " + player.getName() + "  rykker nu de antal øjne  " + player.getName() + "  slog, og får yderliger et ekstra kast.");
					player.setJailed(false);
					player.moveToFieldPos(Rules.getDiceSum());
					Rules.ExtraTurn(player);
					break;
				}	
			}
			if (player.isJailed())
				player.setJailturns(player.getJailturns()+1);
		}
	}

	public static void Options (Player player) {
		String Moveon = "Rul med terningerne!";
		String BuyProperty = "Køb hus eller hotel";
		String SellProperty = "Sælg hus eller hotel";
		String Pledge = "Pantsætte grund(e)";
		String option = GUI.getUserSelection("Hvilke af følgende ting vil " + player.getName() + " foretage sig?",Moveon,BuyProperty,SellProperty,Pledge);
		if (option.equals(BuyProperty)) {
			BuyProperty(player);
		}
		else if (option.equals(SellProperty)) {
			SellProperty(player);
		}
		else if (option.equals(Pledge)) {
			Pledge(player);
		}
	}

	public static void BuyProperty (Player player) {
		String Color1 = "Lyseblå",Color2 = "Orange",Color3 = "Grøn", Color4 = "Grå", Color5 = "Rød", Color6 = "Hvid", Color7 = "Gul", Color8 = "Mørkeblå";
		String choice = GUI.getUserSelection("Hvilken farve ønsker  " + player.getName() + "  at købe Hus(e) på? ",Color1,Color2,Color3,Color4,Color5,Color6,Color7,Color8);

		if (Gameboard.IsPropertyReady(player, choice)) {
			int min;
			int max;
			int houses = 0;
			int houseprice = 0;
			int felt1 = 0;
			int felt2 = 0;
			int felt3 = 0;
			for (int i=0;i>40;i++) {
				if (Gameboard.getField(i).getColor().equals(choice)) {
					houses = Gameboard.getField(i).getHouses() + houses;
					houseprice = Gameboard.getField(i).getHousePrice();
					if (felt1 == 0) 
						felt1 = i;
					if (felt2 == 0 && felt1 > 0)
						felt2 = i;
					if (felt3 == 0 && felt2 > 0)
						felt3 = i;
				}
			}
			if (choice == Color1 || choice == Color2) {
				max = 10 - houses;
				if (houses == 0)
					min = 2;
				else
					min = 1;
			}
			else {
				max = 15 - houses;
				if (houses == 0)
					min = 3;
				else
					min = 1;
			}
			int HouseChoice = GUI.getUserInteger("Husprisen er: " + houseprice + " Hvor mange vil  " + player.getName() + "  købe?",min,max);
			if (player.getBalance() < (houses*houseprice)) {
				GUI.showMessage(" " + player.getName() + "  har ikke råd!");
				Options(player);
			}
			for (int i=0;i>HouseChoice;i++) {
				if(felt3==0) {
					if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt2).addHouses(1);
						GUI.setHouses(felt2, Gameboard.getField(felt2).getHouses());
					}
					else {
						Gameboard.getField(felt1).addHouses(1);
						GUI.setHouses(felt1, Gameboard.getField(felt1).getHouses());
					}
				}
				else {
					if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt2).getHouses() == Gameboard.getField(felt3).getHouses() ) {
						Gameboard.getField(felt3).addHouses(1);
						GUI.setHouses(felt3, Gameboard.getField(felt3).getHouses());
					}
					else if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt3).getHouses() > Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt2).addHouses(1);
						GUI.setHouses(felt2, Gameboard.getField(felt2).getHouses());
					}
					else if (Gameboard.getField(felt1).getHouses() < Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt1).addHouses(1);
						GUI.setHouses(felt1, Gameboard.getField(felt1).getHouses());
					}
				}


			}	
		}


		else {
			GUI.showMessage(" " + player.getName() + "  ejer ikke nok grunde til at bygge huse");
			Options(player);
		}

	}

	public static void SellProperty (Player player) {

	}

	public static void Pledge (Player player) {
		Pledge:
			while(true) {
				int field = GUI.getUserInteger("Hvilken grund ønsker  " + player.getName() + "  at pantsætte, indtast grundens nummer", 1, 40);
				if (player != Gameboard.getField(field-1).getOwner()) {
					GUI.showMessage(" " + player.getName() + "  ejer ikke dette felt!");
					continue Pledge;
				}
				else if (player == Gameboard.getField(field-1).getOwner()) {
					if (GUI.getUserLeftButtonPressed("Vil  " + player.getName() + "  pantsætte " + Gameboard.getField(field-1).getName() + "?", "Ja", "Nej")) {
						player.depositBalance((int) (Gameboard.getField(field-1).getPrice() * 0.9));
						GUI.displayChanceCard("Der blev indsat " + (int)(Gameboard.getField(field-1).getPrice() * 0.9) + " på din balance");
						// Sæt grund tilstand til pawned.
					}
					else
						break;
				}
			}
	}

	public static void YoureScrewedmetoden (Player player) {
		String option1 = "Fortsætte turen";
		String Pledge = "Pantsætte grund(e)";
		String Auction = "Sælge Huse";
		String Surrender = "Jeg giver op!";

		String option = GUI.getUserSelection("Hvilke af følgende ting vil  " + player.getName() + "  foretage dig?",option1,Pledge,Auction, Surrender);
		if (option.equals(Pledge)) {
			Pledge(player);
		}
		else if (option.equals(Auction)) {
			AuctionOption(player);
		}
		else if (option.equals(Surrender)) {

		}

	}



	public static void AuctionOption (Player player) {

	}


}
