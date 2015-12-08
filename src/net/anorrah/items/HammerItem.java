package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.knockbackWeaponBonus;

public class HammerItem extends MeleeWeaponItem  {

	public HammerItem(int levelBonus) {
		super(levelBonus);
		damage = 10+enchantment;
		super.itemDescription = "Stop, hammer time. This weapon deals 10 (+ "+enchantment+") points of damage per swing. Push enemies back on hit.";
		myBonus.add(new MeleeWeaponBonus(damage));
		
		myBonus.add(new knockbackWeaponBonus());
		
	}
/*
	@Override
	public String description() 
	{
		return "Hammer";
	}*/

	
	
}
