package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;


public class castObject extends otherObject {

	private int restoreATurn = 10;

	
	public castObject(int currentLevel) {
		super(currentLevel);
		charges = 4;
		myBonus.add(new regenerationBonus(restoreATurn, 7));
		super.itemDescription ="heals 10 hp per second for  7 secondsn";
	}

/*	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
