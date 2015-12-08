package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;

public class FireballRod extends RangedWeaponItem {

	
	public FireballRod(int currentLevel) {
		super(currentLevel);
		hasCharges = true;
		charges = 7;
		damage = (2*enchantment)+20;
		myBonus.add(new fireballBonus());
		// TODO Auto-generated constructor stub
		name= "Fireball Rod";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
