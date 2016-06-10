package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.berserkerBoost;
import net.anorrah.items.bonus.regenerationBonus;

public class BerserkerItems extends otherItemObject{

	public BerserkerItems(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
		charges = 5;
		super.itemDescription ="Not your day. Take damage in exchange for a damage boost.";
		name = "Berserker's Soul";
		
	}
	
	
	public void onUseOnSelf(Entity user) {
		
		onlyForRegen = new berserkerBoost();
		super.onUseOnSelf(user);

	}
	
}
