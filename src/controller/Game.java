package controller;

import entity.Gameboard;
import entity.PlayerOptions;
import entity.Rules;


public class Game {

	public static void main(String[] args) {

		Gameboard.UpdateGUI();
		Rules.SetupGame();
		
		while (Rules.getWin() == false) 
		{
			for (int i=0;i<6;i++) 
			{
				if (Rules.getPlayer(i) != null) 
				{
					if (Rules.getPlayer(i).isJailed()) 
						PlayerOptions.Jailturn(Rules.getPlayer(i));
					else
						Rules.Turn(Rules.getPlayer(i));
				}
			}
		}
	}
}
