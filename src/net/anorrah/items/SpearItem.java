package net.anorrah.items;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.spearBonus;

public class SpearItem extends MeleeWeaponItem
{
	
		
		

	public SpearItem(int levelBonus) {
		
		super(levelBonus);
		damage = 8+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		super.itemDescription = "Spear";
		myBonus.add(new spearBonus());
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Spear";
	}
	
}
