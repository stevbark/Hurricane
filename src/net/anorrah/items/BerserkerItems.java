package net.anorrah.items;

import net.anorrah.items.bonus.berserkerBoost;

public class BerserkerItems extends otherItemObject{

	public BerserkerItems(int currentLevel) {
		super(currentLevel);
		myBonus.add(new berserkerBoost());
		hasCharges = true;
		charges = 5;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
