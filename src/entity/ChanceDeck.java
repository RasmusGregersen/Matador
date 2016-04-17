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
		deck[0] = new ChanceCard("Description",1);

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
		GUI.showMessage(deck[pickCount].getDescription());
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
			GUI.displayChanceCard(deck[number].getDescription());
			player.depositBalance(1000);
			break;
		case 2:

		}
	}

}

