package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;

public class FoodItem extends ItemObject {

	private int restoreATurn = 10;
	 
	public FoodItem(int currentLevel) {
		super(currentLevel);
		charges = 2;
		myBonus.add(new regenerationBonus(restoreATurn, 10));
		super.itemDescription ="SO rotten. heals 10 hp per second for 10 seconds. cannot move while eating.";
	}

/*	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
