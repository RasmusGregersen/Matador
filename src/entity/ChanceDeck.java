package entity;

import desktop_resources.GUI;

import java.util.Random;

public class ChanceDeck {
	private ChanceCard[] deck = new ChanceCard[33];
	private ChanceCard[] chancecards = new ChanceCard[33];
	private int pickCount = 0;
	private Random rnd = new Random();

	public ChanceDeck() {
		CreateCards();
		ShuffleDeck();
	}

	public void CreateCards() {
		chancecards[0] = new ChanceCard("De modtager Deres aktieudbytte. Modtag 1000 kr,- fra Banken",1);
		chancecards[1] = new ChanceCard("Ryk frem til start",2);
		chancecards[2] = new ChanceCard("Gå i fængsel. Ryk direkte i fængslet. Selv om de Passerer start indkassere de ikke 4000 kr,-",3);
		chancecards[3] = new ChanceCard("Gå i fængsel. Ryk direkte i fængslet. Selv om de Passerer start indkassere de ikke 4000 kr,-",3);
		chancecards[4] = new ChanceCard("De har været en tur i udlandet og har haft for mange cigaretter med hjem. Betal 200 kr,- i told",4);
		chancecards[5] = new ChanceCard("De har modtaget deres tandlægeregning. Betal 2000 kr,-",5);
		chancecards[6] = new ChanceCard("De havde en række elleve rigtige i tipning. Modtag 1000 kr,-",6);
		chancecards[7] = new ChanceCard("Deres præmieolbigation er kommet ud. De modtager 1000 kr,- af Banken",6);
		chancecards[8] = new ChanceCard("Deres præmieolbigation er kommet ud. De modtager 1000 kr,- af Banken",6);
		chancecards[9] = new ChanceCard("Det er deres fødselsdag. Modtag 200 kr,- fra hver spiller",7);
		chancecards[10] = new ChanceCard("Værdien af egen avl fra nyttehaven udgør 200 kr,-. De modtager disse penge af Banken",8);
		chancecards[11] = new ChanceCard("Betal Deres bilforsikring på 1000 kr,-",9);
		chancecards[12] = new ChanceCard("Ejendomsskatterne er steget, ekstraudgifterne er: 800 kr,- per hus, og 2300 kr,- per hotel",10);
		chancecards[13] = new ChanceCard("Ryk frem til Grønningen, hvis de passerer start modtag 4000 kr,-.",11);
		chancecards[14] = new ChanceCard("Ryk din brik til det nærmeste rederi, hvis rederiet er ejet, betal det dobbelte, af hvad ejeren af berettiget til. Hvis det ikke ejes, kan du købe grunden.",12);
		chancecards[15] = new ChanceCard("Ryk din brik til det nærmeste rederi, hvis rederiet er ejet, betal det dobbelte, af hvad ejeren af berettiget til. Hvis det ikke ejes, kan du købe grunden.",12);
		chancecards[16] = new ChanceCard("Tag med LB-færgerne flyt brikken frem, og hvis de Passerer Start inkasser da 4000 kr,-",13);

	}

	public void ShuffleDeck() {
		for (int i=0;i<chancecards.length;i++) {
			int RandomPosition = rnd.nextInt(chancecards.length);
			deck[RandomPosition] = chancecards[i];
		}
	}

	public void DrawCard(Player player) {
		GUI.showMessage("Træk et \"prøv lykken\"-kort");
		GUI.displayChanceCard(chancecards[pickCount].getDescription());
		effect(player, chancecards[pickCount].getEffect() );
		pickCount++;
		if (pickCount == 33) {
			ShuffleDeck();
			pickCount = 0;
		}
	}

	public void effect(Player player, int number) {
		switch (number) {
		case 1:
			player.depositBalance(1000);
			break;
		case 2:
			player.setFieldPos(0);
			break;
		case 3:
			Rules.GoToJail(player);
			break;
		case 4:
			player.withdrawBalance(200);
			break;
		case 5:
			player.withdrawBalance(2000);
			break;
		case 6:
			player.depositBalance(1000);
			break;
		case 7:
			for (int i = 0; i < 6; i++) {
				Rules.getPlayer(i).withdrawBalance(200);
			}
			player.depositBalance(200*Rules.getPlayers());
			break;
		case 8:
			player.depositBalance(200);
			break;
		case 9:
			player.depositBalance(1000);
			break;
		case 10:
			int housecounter = 0;
			int hotelcounter = 0;
			for (int i = 0; i < 40;i++) {
				if (Gameboard.getField(i).getOwner() == player) {
					if (Gameboard.getField(i).getHouses() == 5)
						hotelcounter++; 
					else if (Gameboard.getField(i).getHouses() < 5)
						housecounter = housecounter + Gameboard.getField(i).getHouses();
				}
			}
			int total = 2300*hotelcounter + 800*housecounter;
			player.withdrawBalance(total);
			GUI.showMessage("Du ejer " + housecounter + " huse og " + hotelcounter + " hoteller. Derfor skal du betale " + total + " kr,- til Banken");
		case 11:
			player.setFieldPos(25);
			Gameboard.setField(player.getFieldPos(), player);
			if (player.getFieldPos() == 34 || player.getFieldPos() == 37);
			player.depositBalance(4000);
		case 12:
			if (player.getFieldPos() == 37 || player.getFieldPos() == 6) {
				player.setFieldPos(6);
				Gameboard.setField(player.getFieldPos(), player);
				if (Gameboard.getField(6).getOwner() != null && Gameboard.getField(6).getOwner() != player)
					player.withdrawBalance(Gameboard.getField(6).getRent());
			}
		case 13:
			player.setFieldPos(6);
			Gameboard.setField(player.getFieldPos(), player);
			if (player.getFieldPos() == 3)
				break;
			else {
				player.depositBalance(4000);
				break;
			}
		case 14:
		}
	}
}

