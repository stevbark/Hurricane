package net.anorrah.items;

import net.anorrah.items.bonus.AxeBonus;
import net.anorrah.items.bonus.MeleeWeaponBonus;

public class AxeItem extends MeleeWeaponItem {

	public AxeItem(int levelBonus) {
		super(levelBonus);
		super.itemDescription = "Axe";
		
		damage = 10+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		myBonus.add(new AxeBonus());
		
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Axe";
	}

}
