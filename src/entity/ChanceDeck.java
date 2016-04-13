package entity;

import desktop_resources.GUI;
import java.util.Random;

public class ChanceDeck {
	private ChanceCard[] deck = new ChanceCard[33];
	private int cardCount = 0;
	private int pickCount = 0;
	
	public void DrawCard(Player player) {
		GUI.showMessage("Træk et \"prøv lykken\"-kort");
		GUI.showMessage(deck[pickCount].getDescription());
		effect(player, deck[pickCount].getAction());
		pickCount++;		
	}
	public void createCards() {
		deck[0] = new ChanceCard("Description",1);
		
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
	
	public void shuffleDeck(ChanceCard[] deck) {
		Random rnd = new Random();
	    
		for (int i = deck.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      ChanceCard a = deck[index];
	      deck[index] = deck[i];
	      deck[i] = a;
	    }
	}
	
}
