package entity;

import java.lang.reflect.Array;

public class Rules {
	
	private static int[] dice = new int [2];
	
	public static void rollDice() {
		dice[0] = (int) Math.ceil(Math.random()*6);
		dice[1] = (int) Math.ceil(Math.random()*6);
	}

	public static int getDie1() {
		return Array.getInt(dice, 0);
	}
	
	public static int getDie2() {
		return Array.getInt(dice, 1);
	}
	
	public static int getDiceSum() {
		return dice[0]+dice[1];
	}
	
}