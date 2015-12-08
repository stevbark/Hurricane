package net.anorrah.items;

import net.anorrah.items.bonus.MeleeWeaponBonus;

public class SwordItem extends MeleeWeaponItem {
	
	String str;
	public SwordItem(String r, int currentLevel)
	{
		super(currentLevel);
		damage = 10+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		name = "Sword";
		
		str = r;
		
		super.itemDescription = "a trusty weapon. This weapon deals 10 (+"+enchantment+") points of damage per swing.";
	}


	
	/*@Override
	public String description() {
		
		return "stabby stabby stab! " + enchantment;
	}
	
	*/
	
	
}
