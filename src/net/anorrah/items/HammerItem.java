package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.knockbackWeaponBonus;

public class HammerItem extends MeleeWeaponItem  {
	
	public HammerItem(int levelBonus) {
		
		super(levelBonus);
		damage = 10+enchantment;
		super.itemDescription = "Hammer";
		myBonus.add(new MeleeWeaponBonus(damage));
		name="Hammer";
		myBonus.add(new knockbackWeaponBonus());
	}

	@Override
	public String description() 
	{
		return "Hammer";
	}

	
	
}
