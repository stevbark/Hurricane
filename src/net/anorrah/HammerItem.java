package net.anorrah;

import java.util.ArrayList;

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
