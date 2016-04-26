package entity;

import desktop_resources.GUI;

public class PlayerOptions {

	public static void Jailturn(Player player) {
		if (player.getJailturns() == 3) {
			player.setJailed(false);
			GUI.showMessage("Du har nu været i fængsel i tre turer, og bliver derfor løsladt, men du er tvunget til, at betale en bøde på 1000 kr,-");
			player.withdrawBalance(1000);
			Rules.Turn(player);
		}

		else if (player.getJailcard() > 0) {
			if (GUI.getUserLeftButtonPressed("Vil du bruge dit Chancekort til at komme ud af fængslet?", "Ja", "Nej")) {
				player.setJailcard(player.getJailcard()-1);
				player.setJailed(false);
			}
		}
		if (GUI.getUserLeftButtonPressed("Vil du betale en bøde på 1000 og komme ud af fængsel","Ja","Nej") && player.getBalance() > 1000 && player.isJailed()) {
			player.withdrawBalance(1000);
			player.setJailed(false);
		}
		else {
			GUI.showMessage("Du har nu 3 forsøg til at slå dobbelt, og komme ud af fængsel");
			for (int i=0;i<3;i++) {
				GUI.getUserButtonPressed("It's " + player.getName() + "'s turn!", "Roll");
				Rules.rollDice();
				if (Rules.getDie1() == Rules.getDie2()) {
					GUI.showMessage("Du slap ud! Du rykker nu de antal øjne du slog, og får yderliger et ekstra kast.");
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
		String Moveon = "Giv turen videre";
		String BuyProperty = "Køb hus eller hotel";
		String SellProperty = "Sælg hus eller hotel";
		String Pledge = "Pantsætte grund(e)";
		String option = GUI.getUserSelection("Hvilke af følgende ting vil du foretage dig?",Moveon,BuyProperty,SellProperty,Pledge);
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
		String choice = GUI.getUserSelection("Hvilken farve ønsker du at købe Hus(e) på? ",Color1,Color2,Color3,Color4,Color5,Color6,Color7,Color8);
		int counter;
		for (int e=0;e<40;e++) {
			if (Gameboard.getField(e).getColor().equals(choice)) {
				if (Gameboard.getField(e).getOwner() == player) {
					counter++;
				}
			}
		}
		if (choice.equals(Color1) || choice.equals(Color8)) {
			if (counter < 2) {
				GUI.showMessage("Du kan ikke bygge huse, da du ikke ejer begge grunde.");
				Options(player);
			}
		}
		else
			if (counter < 3) {
				GUI.showMessage("Du kan ikke bygge huse, da du ikke ejer alle 3 grunde.");
				Options(player);
			}
		

		int houses = GUI.getUserInteger("Husprisen er: " + Gameboard.getField(2).getHousePrice() + " Hvor mange vil du købe?",1,10);
		if(player.getBalance() > (houses*Gameboard.getField(2).getHousePrice())) {
			for (int i=0; i < houses; i ++) {
				player.withdrawBalance(Gameboard.getField(2).getHousePrice());
				if (Gameboard.getField(2).getHouses() >= Gameboard.getField(4).getHouses()){
					Gameboard.getField(4).setHouses();
				}
				else {
					Gameboard.getField(2).setHouses();
				}
			}

		}


	}
	else {
		GUI.showMessage("Du har ikke nok penge til, at købe det antal hus(e)");
		Options(player);
	}
}
else {
	GUI.showMessage("Du ejer ikke alle grundene.");
	Options(player);
}

}
// Check spiller har alle grunde
// Check balance mod samlet huspris.


}

public static void SellProperty (Player player) {

}

public static void Pledge (Player player) {
	Pledge:
		while(true) {
			int field = GUI.getUserInteger("Hvilken grund ønsker du at pantsætte, indtast grundens nummer", 1, 40);
			if (player != Gameboard.getField(field-1).getOwner()) {
				GUI.showMessage("Du ejer ikke dette felt!");
				continue Pledge;
			}
			else if (player == Gameboard.getField(field-1).getOwner()) {
				if (GUI.getUserLeftButtonPressed("Vil du pantsætte " + Gameboard.getField(field-1).getName() + "?", "Ja", "Nej")) {
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

	String option = GUI.getUserSelection("Hvilke af følgende ting vil du foretage dig?",option1,Pledge,Auction, Surrender);
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
