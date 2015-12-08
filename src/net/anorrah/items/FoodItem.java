package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;

public class FoodItem extends ItemObject {

	private int restoreATurn = 10;
	public FoodItem(int currentLevel) {
		super(currentLevel);
		charges = 2;
		myBonus.add(new regenerationBonus(restoreATurn, 10));
		name="Food";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "MMmmm Mmm good";
	}
}
