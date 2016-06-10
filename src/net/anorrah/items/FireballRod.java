package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;

public class FireballRod extends RangedWeaponItem {

	
	public FireballRod(int currentLevel) {
		super(currentLevel);
		damage = (2*enchantment)+20;
		myBonus.add(new fireballBonus());
		super.itemDescription = "The perfect solution to any problem.  A ball of fire that blows up all your enemies dealing 20 (+ "+2*enchantment+" ) points of damage on impact. Also starts a fire.";
		name= "Fireball Rod";
	}



}
