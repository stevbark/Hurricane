package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.regenerationBonus;

public class FoodItem extends ItemObject {

	private int restoreATurn = 10;
	public FoodItem(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
		charges = 2;
		

		name="Food";

		super.itemDescription ="SO rotten. heals 10 hp per second for 10 seconds. cannot move while eating.";
	}
	
	public void onUseOnSelf(Entity user) {
		myBonus.add(new regenerationBonus(restoreATurn, 10));
		
	}

/*	@Override
	public String description() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return "MMmmm Mmm good";
	}
=======
		return null;
	}*/
}
