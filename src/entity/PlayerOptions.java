package entity;

import desktop_resources.GUI;

public class PlayerOptions {

	public static void Jailturn(Player player) {
		if (player.getJailcard() > 0) {
			GUI.displayChanceCard("Du har brugt dit Chancekort til at komme ud af fængslet!");
			Rules.Turn(player);
		}

		else if (GUI.getUserLeftButtonPressed("Vil du betale en bøde på 1000 og kom ud af fængsel","Ja","Nej")) {
			if (player.getBalance() > 1000) {
				player.withdrawBalance(1000);
				// player.setjail = false
			}
		}
	}

	public static void PropertyOption (Player player) {
		String option1 = "Fortsætte turen";
		String Pledge = "Pantsætte grund(e)";
		String Auction = "Sælge Huse";

		String option = GUI.getUserSelection("Hvilke af følgende ting vil du foretage dig?",option1,Pledge,Auction);
		if (option.equals(Pledge)) {
			PledgeOption(player);
		}
		else if (option.equals(Auction)) {
			AuctionOption(player);
		}

	}

	public static void PledgeOption (Player player) {
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


	public static void AuctionOption (Player player) {

	}


}
