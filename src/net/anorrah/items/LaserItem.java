package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;
import net.anorrah.items.bonus.laserBonus;

public class LaserItem extends RangedWeaponItem{
	

	public LaserItem(int levelBonus) {
		super(levelBonus);
		hasCharges = true;
		charges = 7;
		damage = (1*enchantment)+10;
<<<<<<< HEAD
	//	myBonus.add(new ());
		name="Laser";
=======
		myBonus.add(new laserBonus((int) damage));
>>>>>>> c7b58b8bbf2e21bde3279f66095861884c4c5644
		
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
