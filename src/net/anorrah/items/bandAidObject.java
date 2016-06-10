package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.regenerationBonus;

public class bandAidObject extends otherObject {

	private int restoreATurn = 10;
	 
	public bandAidObject(int currentLevel) {
		super(currentLevel);
		hasCharges = true;

		charges = 3;

		name = "Band-Aid";

		super.itemDescription = "now with Hello Kitty. Heals 10 hp per second for 5 seconds.";

	}

	
	protected void use()
	{
	}
	
	public void onUseOnSelf(Entity user) {
		onlyForRegen = new regenerationBonus(restoreATurn,5);
		super.onUseOnSelf(user);

	}
	
	

}
