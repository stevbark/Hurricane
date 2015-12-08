package net.anorrah.items;

import net.anorrah.items.bonus.bowAndArrowBonus;
import net.anorrah.items.bonus.laserBonus;

public class bowAndArrowItem extends RangedWeaponItem {

	public bowAndArrowItem(int levelBonus) {
		super(levelBonus);
		hasCharges = true;
		charges = 30;
		damage = (1*enchantment)+10;
		myBonus.add(new bowAndArrowBonus((int) damage));
		super.itemDescription ="Pew pew: This weapon deals 10 (+ "+enchantment+") points of damage per shot. This weapon is ranged.";

		// TODO Auto-generated constructor stub
		name="Bow";
	}

}
