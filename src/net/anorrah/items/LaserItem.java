package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;

public class LaserItem extends RangedWeaponItem{
	

	public LaserItem(int levelBonus) {
		super(levelBonus);
		hasCharges = true;
		charges = 7;
		damage = (1*enchantment)+10;
	//	myBonus.add(new ());
		name="Laser";
		
		// TODO Auto-generated constructor stub
	}

	
	
}
