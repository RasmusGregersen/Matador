package entity;

import desktop_resources.GUI;

public class PlayerOptions {

	public static void Jailturn(Player player) {
		if (player.getJailturns() == 3) {
			player.setJailed(false);
			GUI.showMessage(player.getName() + "  har nu været i fængsel i tre turer, og bliver derfor løsladt, men  " + player.getName() + "  er tvunget til, at betale en bøde på 1000 kr,-");
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
			GUI.showMessage(player.getName() + "  har nu 3 forsøg til at slå dobbelt, og komme ud af fængsel");
			for (int i=0;i<3;i++) {
				GUI.getUserButtonPressed("Det er " + player.getName() + "'s tur", "Rul terningen");
				Rules.rollDice();
				if (Rules.getDie1() == Rules.getDie2()) {
					GUI.showMessage(player.getName() + "  slap ud!  " + player.getName() + "  rykker nu de antal øjne som du slog, og får yderligere et ekstra kast.");
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
		String Roll = "Rul med terningerne!";
		String BuyProperty = "Køb hus eller hotel";
		String SellProperty = "Sælg hus eller hotel";
		String SetPawned = "Pantsæt grund(e)";
		String BuyPawned = "Køb pantsatte grunde tilbage";
		String option = GUI.getUserSelection("Hvilke af følgende ting vil " + player.getName() + " foretage sig?",Roll,BuyProperty,SellProperty,SetPawned,BuyPawned);
		if (option.equals(BuyProperty)) {
			BuyProperty(player);
		}
		else if (option.equals(SellProperty)) {
			SellProperty(player);
		}
		else if (option.equals(SetPawned)) {
			SetPawned(player);
		}
		else if (option.equals(BuyPawned)) {
			BuyPawned(player);
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
			if (player.getBalance() < (HouseChoice*houseprice)) {
				GUI.showMessage(" " + player.getName() + "  har ikke råd!");
				Options(player);
			}
			player.setTotalAssets(houseprice*HouseChoice);
			for (int i=0;i>HouseChoice;i++) {
				if(felt3==0) {
					if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt2).addHouses(1);
						HouseorHotel(felt2);
					}
					else {
						Gameboard.getField(felt1).addHouses(1);
						HouseorHotel(felt1);
					}
				}
				else {
					if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt2).getHouses() == Gameboard.getField(felt3).getHouses() ) {
						Gameboard.getField(felt3).addHouses(1);
						HouseorHotel(felt3);
					}
					else if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt3).getHouses() > Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt2).addHouses(1);
						HouseorHotel(felt2);
					}
					else if (Gameboard.getField(felt1).getHouses() < Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt1).addHouses(1);
						HouseorHotel(felt1);
					}
				}
			}	
		}

		else {
			GUI.showMessage(player.getName() + " ejer ikke nok grunde til at bygge huse");
			Options(player);
		}
	}


	public static void SellProperty (Player player) {
		String Color1 = "Lyseblå",Color2 = "Orange",Color3 = "Grøn", Color4 = "Grå", Color5 = "Rød", Color6 = "Hvid", Color7 = "Gul", Color8 = "Mørkeblå";
		String choice = GUI.getUserSelection("Hvilken farve ønsker  " + player.getName() + "  at sælge Hus(e) fra? ",Color1,Color2,Color3,Color4,Color5,Color6,Color7,Color8);
		if (Gameboard.IsPropertyReady(player, choice)) {
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
			if (houses == 0) {
				GUI.showMessage(player.getName() + " har ikke nogle huse at sælge...");
				Options(player);
			}
			int HouseChoice = GUI.getUserInteger("Husprisen er: " + houseprice + " og du kan sælge dem for halvdelen. Hvor mange vil " + player.getName() + " sælge?",1,houses);
			for (int i=0;i>HouseChoice;i++) {
				if(felt3==0) {
					int TempTotal; 
					TempTotal = Gameboard.getField(felt1).getHouses() + Gameboard.getField(felt2).getHouses();
					String Yes = "Ja";
					String No = "Nej";
					if (TempTotal-HouseChoice < 2 && TempTotal-HouseChoice > 0) {
						String result = GUI.getUserButtonPressed(player.getName() + "forsøger at sælge huse, så du kun har ét hus på en grund... Dette er i strid mod reglerne. Ønsker du, at sælge alle dine huse? ", Yes, No);
						if(result.equals(Yes)) {
							Gameboard.getField(felt2).addHouses(-1);
							//evt fuldstændig fjern huse fra pågældende grunde.
							HouseorHotel(felt2);
							player.setTotalAssets(-(houseprice*1));
							player.depositBalance((houseprice/2));
						}
						else {
							Options(player);
						}
					}

					else if (Gameboard.getField(felt2).getHouses() == Gameboard.getField(felt1).getHouses()) {
						Gameboard.getField(felt1).addHouses(-1);
						HouseorHotel(felt1);
					}
					else {
						Gameboard.getField(felt2).addHouses(-1);
						HouseorHotel(felt2);
					}
				}
				else {
					int TempTotal; 
					TempTotal = Gameboard.getField(felt1).getHouses() + Gameboard.getField(felt2).getHouses();
					String Yes = "Ja";
					String No = "Nej";
					if (TempTotal-HouseChoice < 3 && TempTotal-HouseChoice > 0) {
						String result = GUI.getUserButtonPressed(player.getName() + "forsøger at sælge huse, så du kun har to hus på to grunde... Dette er i strid mod reglerne. Ønsker du, at sælge alle dine huse? ", Yes, No);
						if(result.equals(Yes)) {
							Gameboard.getField(felt1).addHouses(-1);
							HouseorHotel(felt1);
							Gameboard.getField(felt2).addHouses(-1);
							HouseorHotel(felt2);
							player.setTotalAssets(-(houseprice*2));
							player.depositBalance((houseprice*2/2));
						}
						else {
							Options(player);
						}
					}
					if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt2).getHouses() == Gameboard.getField(felt3).getHouses() ) {
						Gameboard.getField(felt1).addHouses(-1);
						HouseorHotel(felt1);
					}
					else if (Gameboard.getField(felt1).getHouses() == Gameboard.getField(felt2).getHouses() && Gameboard.getField(felt3).getHouses() > Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt2).addHouses(-1);
						HouseorHotel(felt2);
					}
					else if (Gameboard.getField(felt1).getHouses() < Gameboard.getField(felt2).getHouses()) {
						Gameboard.getField(felt3).addHouses(-1);
						HouseorHotel(felt3);
					}
				}
			}
			player.setTotalAssets(-(houseprice*HouseChoice));
			player.depositBalance((houseprice*HouseChoice/2));
		}
		

			else {
				GUI.showMessage(player.getName() + " har ikke ret til at sælge grunde her!");
				Options(player);
			}
		}

		public static void SetPawned (Player player) {
				while(true) {
					int field = GUI.getUserInteger("Hvilken grund ønsker  " + player.getName() + "  at pantsætte, indtast grundens nummer", 1, 40);
					if (player != Gameboard.getField(field).getOwner()) {
						GUI.showMessage(" " + player.getName() + "  ejer ikke dette felt!");
						Options(player);
					}
					else if (player == Gameboard.getField(field).getOwner()) {
						if (Gameboard.getField(field).getHouses() > 0) {
							GUI.showMessage(player.getName() + " skal sælge dine huse, før du kan pantsætte din grund.");
							Options(player);
						}
						if (GUI.getUserLeftButtonPressed("Vil  " + player.getName() + "  pantsætte " + Gameboard.getField(field).getName() + "?", "Ja", "Nej")) {
							player.depositBalance((int) (Gameboard.getField(field).getPrice() * 0.9));
							player.setTotalAssets(-(Gameboard.getField(field).getPrice()));
							GUI.showMessage("Der blev indsat " + (int)(Gameboard.getField(field).getPrice() * 0.9) + " på din balance");
							//HVORFOR FUCK GIVER DEN FEJL? RASMUS...
							Gameboard.getField(field).setPawned(true);
						}
						else
							break;
					}
				}
		}

		public static void BuyPawned (Player player) {
				
		}
		

		public static void HouseorHotel (int felt) {
			if (Gameboard.getField(felt).getHouses() < 5){
				GUI.setHouses(felt, Gameboard.getField(felt).getHouses());
			}
			else {
				GUI.setHotel(felt, true);
			}
		}

		public static void YoureScrewedmetoden (Player player) {
			String Pledge = "Pantsætte grund(e)";
			String Auction = "Sælge Huse";
			String Surrender = "Jeg giver op!";
			if (player.getBalance() <= 0) {
				GUI.showMessage(player.getName() + " Din balance er negativ. Tryk ok for at se dine muligheder");
				String option = GUI.getUserSelection("Hvilke af følgende ting vil  " + player.getName() + "  foretage sig?",Pledge,Auction, Surrender);
				if (option.equals(Pledge)) {
					BuyPawned(player);
				}
				else if (option.equals(Auction)) {
					AuctionOption(player);
				}
				else if (option.equals(Surrender)) {
					Rules.LoseCondition(player);
			}
			
			}

		}

		public static void AuctionOption (Player player) {

		}


	}
