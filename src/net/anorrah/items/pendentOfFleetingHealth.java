package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.regenerationBonus;
import net.anorrah.items.bonus.tempHealthBonus;

public class pendentOfFleetingHealth extends otherObject {
	
	public pendentOfFleetingHealth(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
charges=3;
		super.itemDescription = "A shield that blocks 30 points of damage that lasts for 4 turns. ";
		myBonus.add(new tempHealthBonus(4,30));
		name="Pendant of Fleeting Health";
	}

	
	public void onUseOnSelf(Entity user) {
		
		onlyForRegen = new tempHealthBonus(4,30);

		myBonus.add(new tempHealthBonus(4,30));
		super.onUseOnSelf(user);
	}


}
