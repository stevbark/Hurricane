package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;
import net.anorrah.items.bonus.laserBonus;

public class LaserItem extends RangedWeaponItem{

	public LaserItem(int levelBonus) {
		super(levelBonus);
		hasCharges = true;
		charges = 7;
		damage = +10;
		myBonus.add(new laserBonus((int) damage));
		super.itemDescription = "Bzzzzzzt: Deal 10 damage to everyone in a straight line.";
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
