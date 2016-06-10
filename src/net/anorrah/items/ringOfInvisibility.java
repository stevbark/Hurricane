package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.invisibilityBonus;
import net.anorrah.items.bonus.tempHealthBonus;

public class ringOfInvisibility extends otherObject {
	
	
	public ringOfInvisibility(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
charges=3;
		myBonus.add(new invisibilityBonus(3));
		super.itemDescription =" Ring of Invisibility:      untargetable for 3 turns.";
		name="Ring of Invisibility";
	}

	public void onUseOnSelf(Entity user) {
		onlyForRegen = new invisibilityBonus(3);
		super.onUseOnSelf(user);
	}
	

}
