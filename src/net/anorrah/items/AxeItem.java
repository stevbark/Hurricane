package net.anorrah.items;

import net.anorrah.items.bonus.AxeBonus;
import net.anorrah.items.bonus.MeleeWeaponBonus;

public class AxeItem extends MeleeWeaponItem {

	public AxeItem(int levelBonus) {
		super(levelBonus);
		super.itemDescription = "Stop, hammer time. This weapon deals 10 (+ "+enchantment+" level) points of damage per swing. Push enemies back on hit.";
		
		damage = 10+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		myBonus.add(new AxeBonus());
		
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return super.itemDescription;
	}
*/
}
