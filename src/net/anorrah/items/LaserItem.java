package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;
import net.anorrah.items.bonus.laserBonus;

public class LaserItem extends RangedWeaponItem{
	

	public LaserItem(int levelBonus) {
		super(levelBonus);
	

		damage = (1*enchantment)+10;

		name="Laser";

		damage = +10;

		myBonus.add(new laserBonus((int) damage));
		super.itemDescription = "Bzzzzzzt: Deal 10 damage to everyone in a straight line.";
	}

	
	
	
	
}
