package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.Entity;
import net.anorrah.items.bonus.EnfeeblementBonus;
import net.anorrah.items.bonus.VampiricBonus;
import net.anorrah.items.bonus.bonus;
import net.anorrah.items.bonus.fireMeleeBonus;
import net.anorrah.items.bonus.regenerationBonus;

public class FoodItem extends ItemObject {

	private int restoreATurn = 10;
	public FoodItem(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
		charges = 2;

		name="Food";

		super.itemDescription ="SO rotten. heals 10 hp per second for 10 seconds.";
	}
	
	
	protected void use()
	{
		
	}
	public void onUseOnSelf(Entity user) {
		onlyForRegen = new regenerationBonus(restoreATurn, 10);
		super.onUseOnSelf(user);
	}


}
