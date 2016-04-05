package fields;

import desktop_resources.GUI;
import entity.Player;

public class GoToJail extends Field {

	public GoToJail(String name) {
		super(name);
	}
	

	@Override
	public void landOnField(Player player) {
		GUI.showMessage("Du er blevet smidt direkte i fængsel og modtager ikke din bonus ved passering af start");
		GUI.removeCar(30, player.getName());
		
		
	}
	
}
