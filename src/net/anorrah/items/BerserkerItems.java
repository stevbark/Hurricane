package net.anorrah.items;

import net.anorrah.Entity;
import net.anorrah.items.bonus.berserkerBoost;
import net.anorrah.items.bonus.regenerationBonus;

public class BerserkerItems extends otherItemObject{

	public BerserkerItems(int currentLevel) {
		super(currentLevel);
	//	myBonus.add(new berserkerBoost());
		hasCharges = true;
		charges = 5;
		super.itemDescription ="Not your day. Take damage in exchange for a damage boost.";
		// TODO Auto-generated constructor stub
		name = "Berserker's Soul";
		
	}
	
	
	public void onUseOnSelf(Entity user) {
		myBonus.add(new berserkerBoost());
		super.onUseOnSelf(user);

	}
	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
