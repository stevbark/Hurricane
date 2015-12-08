package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;

public class bandAidObject extends otherObject {

	private int restoreATurn = 10;
	 
	public bandAidObject(int currentLevel) {
		super(currentLevel);
		charges = 3;
		myBonus.add(new regenerationBonus(restoreATurn,5));
		name = "Band-Aid";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
