package entity;

import desktop_resources.GUI;

import java.util.Random;

public class ChanceDeck {
	private ChanceCard[] deck = new ChanceCard[33];
	private int pickCount = 0;
	private Random rnd = new Random();

	public ChanceDeck() {
		CreateCards();
		ShuffleDeck();
	}

	public void CreateCards() {
		deck[0] = new ChanceCard("De modtager Deres aktieudbytte. Modtag 1000 kr,- fra Banken",1);
		deck[1] = new ChanceCard("Ryk frem til start",2);
		deck[2] = new ChanceCard("Gå i fængsel. Ryk direkte i fængslet. Selv om de Passerer start indkassere de ikke 4000 kr,-",3);
		deck[3] = new ChanceCard("Gå i fængsel. Ryk direkte i fængslet. Selv om de Passerer start indkassere de ikke 4000 kr,-",3);
		deck[4] = new ChanceCard("De har været en tur i udlandet og har haft for mange cigaretter med hjem. Betal 200 kr,- i told",4);
		deck[5] = new ChanceCard("De har modtaget deres tandlægeregning. Betal 2000 kr,-",5);
		deck[6] = new ChanceCard("De havde en række elleve rigtige i tipning. Modtag 1000 kr,-",6);
		deck[7] = new ChanceCard("Deres præmieolbigation er kommet ud. De modtager 1000 kr,- af Banken",6);
		deck[8] = new ChanceCard("Deres præmieolbigation er kommet ud. De modtager 1000 kr,- af Banken",6);
		deck[9] = new ChanceCard("Det er deres fødselsdag. Modtag 200 kr,- fra hver spiller",7);
		deck[10] = new ChanceCard("Værdien af egen avl fra nyttehaven udgør 200 kr,-. De modtager disse penge af Banken",8);
		deck[11] = new ChanceCard("Betal Deres bilforsikring på 1000 kr,-",9);
		deck[12] = new ChanceCard("Ejendomsskatterne er steget, ekstraudgifterne er: 800 kr,- per hus, og 2300 kr,- per hotel",10);
		deck[13] = new ChanceCard("Ryk frem til Grønningen, hvis de passerer start modtag 4000 kr,-.",11);
		deck[14] = new ChanceCard("Ryk din brik til det nærmeste rederi, hvis rederiet er ejet, betal det dobbelte, af hvad ejeren af berettiget til. Hvis det ikke ejes, kan du købe grunden.",12);
		deck[15] = new ChanceCard("Ryk din brik til det nærmeste rederi, hvis rederiet er ejet, betal det dobbelte, af hvad ejeren af berettiget til. Hvis det ikke ejes, kan du købe grunden.",12);
		deck[16] = new ChanceCard("Tag med LB-færgerne flyt brikken frem, og hvis de Passerer Start inkasser da 4000 kr,-",13);
		
	}

	public void ShuffleDeck() {
		for (int i=0;i<deck.length;i++) {
			int RandomPosition = rnd.nextInt(deck.length);
			ChanceCard temp = deck[i];
			deck[RandomPosition] = temp;
		}
	}

	public void DrawCard(Player player) {
		GUI.showMessage("Træk et \"prøv lykken\"-kort");
		GUI.displayChanceCard(deck[pickCount].getDescription());
		effect(player, deck[pickCount].getEffect() );
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
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 2:
			player.setFieldPos(0);
			break;
		case 3:
			Rules.GoToJail(player);
			break;
		case 4:
			player.withdrawBalance(200);
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 5:
			player.withdrawBalance(2000);
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 6:
			player.depositBalance(1000);
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 7:
			for (int i = 0; i < 6; i++) {
				Rules.getPlayer(i).withdrawBalance(200);
				GUI.setBalance(Rules.getPlayer(i).getName(), Rules.getPlayer(i).getBalance());
			}
			player.depositBalance(200*Rules.getPlayers());
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 8:
			player.depositBalance(200);
			GUI.setBalance(player.getName(), player.getBalance());
			break;
		case 9:
			player.depositBalance(1000);
			GUI.setBalance(player.getName(), player.getBalance());
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
			GUI.setBalance(player.getName(), player.getBalance());
			GUI.showMessage("Du ejer " + housecounter + " huse og " + hotelcounter + " hoteller. Derfor skal du betale " + total + " kr,- til Banken");
		case 11:
			player.setFieldPos(25);
			Gameboard.setField(player.getFieldPos(), player);
			GUI.setCar(player.getFieldPos(), player.getName());
			if (player.getFieldPos() == 34 || player.getFieldPos() == 37);
			player.depositBalance(4000);
			GUI.setBalance(player.getName(), player.getBalance());
		case 12:
		case 13:
			player.setFieldPos(6);
			Gameboard.setField(player.getFieldPos(), player);
			GUI.setCar(player.getFieldPos(), player.getName());
			if (player.getFieldPos() == 3)
			break;
			else 
			player.depositBalance(4000);
			GUI.setBalance(player.getName(), player.getBalance());
		case 14:
			
		}
	}

}

