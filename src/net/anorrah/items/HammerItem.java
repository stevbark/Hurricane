package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.knockbackWeaponBonus;

public class HammerItem extends MeleeWeaponItem  {

	public HammerItem(int levelBonus) {
		super(levelBonus);
		damage = 10+enchantment;

		myBonus.add(new MeleeWeaponBonus(damage));
		
		myBonus.add(new knockbackWeaponBonus());
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
