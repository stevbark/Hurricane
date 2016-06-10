package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.regenerationBonus;

public class potionObject extends otherObject {

	public potionObject(int currentLevel) {
		super(currentLevel);
		
		name="Potion";
		charges = 1;
		hasCharges= true;
		super.itemDescription = "actually a placebo. Instantly heals 30 hp ";
	}
	



	public void onUseOnSelf(Entity user)
	{
		user.heal(30);
		super.onUseOnSelf(user);
	}

}
