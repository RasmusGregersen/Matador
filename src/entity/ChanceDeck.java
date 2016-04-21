package entity;

import desktop_resources.GUI;

import java.util.Random;

public class ChanceDeck {
	private static ChanceCard[] deck = new ChanceCard[33];
	private static ChanceCard[] chancecards = new ChanceCard[33];
	private static int pickCount = 0;

	public static void setPickCount() {
		pickCount++;
		if (pickCount == 33) {
			ShuffleDeck();
			pickCount = 0;
		}
	}

	public static void CreateCards() {
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
		chancecards[17] = new ChanceCard("I anledning af kongens fødselsdag benådes De herved for fængsel.",14);
		chancecards[18] = new ChanceCard("I anledning af kongens fødselsdag benådes De herved for fængsel.",14);
		chancecards[19] = new ChanceCard("Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1000",1);
		chancecards[20] = new ChanceCard("Ryk frem til Frederiksberg Allé. Hvis De passerer Start inkassér kr. 4000",15);
		chancecards[21] = new ChanceCard("De har vundet i Klasselotteriet. Modtag kr. 500 ",16);
		chancecards[22] = new ChanceCard("Tag ind på Rådhuspladsen.",17);
		chancecards[23] = new ChanceCard("Ryk tre felter tilbage.",18);
		chancecards[24] = new ChanceCard("Oliepriserne er steget, og De skal betale: 500 kr,- per hus, og 2000 kr,- per hotel",19);
		chancecards[25] = new ChanceCard("Betal kr. 3000 for reparation af Deres vogn.",20);
		chancecards[26] = new ChanceCard("Betal kr. 3000 for reparation af Deres vogn.",20);
		chancecards[27] = new ChanceCard("De modtager Matador-legatet for værdigt trængende. Hvis deres balance + grunde og bygninger er mindre end kr. 15.000 værd. Modtag kr. 40.000",21);
		chancecards[28] = new ChanceCard("Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3000 ",22);
		chancecards[29] = new ChanceCard("Modtag udbytte af Deres aktier kr. 1000 ",1);
		chancecards[30] = new ChanceCard("Modtag udbytte af Deres aktier kr. 1000 ",1);
		chancecards[31] = new ChanceCard("De har kørt frem for Fuld Stop. Betal kr. 1000 i bøde.",9);
		chancecards[32] = new ChanceCard("De har måttet vedtage en parkeringsbøde. Betal kr. 200 i bøde.",4);
	}

	public static void ShuffleDeck() {
		Random rnd = new Random();
		for (int i=0;i<chancecards.length;i++) {
			int RandomPosition = rnd.nextInt(chancecards.length);
			deck[RandomPosition] = chancecards[i];
		}
	}

	public static void DrawCard(Player player) {
		//String description = deck[pickCount].getDescription();
		int effect = deck[pickCount].getEffect();
		GUI.showMessage("Træk et \"prøv lykken\"-kort");
		//GUI.displayChanceCard(description);
		effect(player,effect);
		ChanceDeck.setPickCount();
	}

	public static void effect(Player player, int number) {
		GUI.displayChanceCard(deck[number].getDescription());
		switch (number) {
		case 1:
			player.depositBalance(1000);
			break;
		case 2:
			player.setFieldPos(1);
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
			player.withdrawBalance(1000);
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
			break;
		case 11:
			if (player.getFieldPos() == 34 || player.getFieldPos() == 37)
				player.depositBalance(4000);
			player.setFieldPos(25);
			Gameboard.setField(player.getFieldPos(), player);
			break;
		case 12:
			if (player.getFieldPos() == 37 || player.getFieldPos() == 3) {
				player.setFieldPos(6);
				Gameboard.setField(player.getFieldPos(), player);
				if (Gameboard.getField(6).getOwner() != null && Gameboard.getField(6).getOwner() != player)
					player.withdrawBalance(Gameboard.getField(6).getRent());
			}
			else if (player.getFieldPos() == 8) {
				player.setFieldPos(16);
				Gameboard.setField(player.getFieldPos(), player);
				if (Gameboard.getField(16).getOwner() != null && Gameboard.getField(16).getOwner() != player)
					player.withdrawBalance(Gameboard.getField(16).getRent());
			}
			else if (player.getFieldPos() == 18 || player.getFieldPos() == 23) {
				player.setFieldPos(26);
				Gameboard.setField(player.getFieldPos(), player);
				if (Gameboard.getField(26).getOwner() != null && Gameboard.getField(26).getOwner() != player)
					player.withdrawBalance(Gameboard.getField(26).getRent());
			}
			else if (player.getFieldPos() == 34) {
				player.setFieldPos(36);
				Gameboard.setField(player.getFieldPos(), player);
				if (Gameboard.getField(36).getOwner() != null && Gameboard.getField(36).getOwner() != player)
					player.withdrawBalance(Gameboard.getField(36).getRent());
			}
			break;
		case 13:
			player.setFieldPos(6);
			Gameboard.setField(player.getFieldPos(), player);
			if (player.getFieldPos() != 3)
				player.depositBalance(4000);
			break;
		case 14:
			//Fængselkort		
			break;
		case 15:
			if (player.getFieldPos() == 18 || player.getFieldPos() == 23 || player.getFieldPos() == 34 || player.getFieldPos() == 37)
				player.depositBalance(4000);
			player.setFieldPos(12);
			Gameboard.setField(player.getFieldPos(), player);
			break;
		case 16:
			player.depositBalance(500);
			break;
		case 17:
			player.setFieldPos(39);
			Gameboard.setField(player.getFieldPos(), player);
			break;
		case 18:
			player.moveToFieldPos(-3);
			Gameboard.setField(player.getFieldPos(), player);
			break;
		case 19:
			housecounter = 0;
			hotelcounter = 0;
			for (int i = 0; i < 40;i++) {
				if (Gameboard.getField(i).getOwner() == player) {
					if (Gameboard.getField(i).getHouses() == 5)
						hotelcounter++; 
					else if (Gameboard.getField(i).getHouses() < 5)
						housecounter = housecounter + Gameboard.getField(i).getHouses();
				}
			}
			total = 2000*hotelcounter + 500*housecounter;
			player.withdrawBalance(total);
			GUI.showMessage("Du ejer " + housecounter + " huse og " + hotelcounter + " hoteller. Derfor skal du betale " + total + " kr,- til Banken");
			break;
		case 20:
			player.withdrawBalance(3000);
			break;
		case 21:
			if (player.getBalance() + player.getTotalAssets() > 15000) {
				player.depositBalance(40000);
				GUI.showMessage("Dine samlede aktiver og passiver udgør: " + player.getTotalAssets()+player.getBalance() + " Og du har dermed vundet matadorlegatet!");
			}
			else
				GUI.showMessage("Dine samlede aktiver og passiver udgør: " + player.getTotalAssets()+player.getBalance() + " Og du har dermed desværre ikke vundet matadorlegatet!");
			break;
		case 22:
			player.depositBalance(3000);
			break;
		}
	}
}

