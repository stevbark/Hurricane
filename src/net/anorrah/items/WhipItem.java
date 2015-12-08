package net.anorrah.items;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.spearBonus;

public class WhipItem extends MeleeWeaponItem 
{
	public WhipItem(int levelBonus) 
	{
		super(levelBonus);
		super.itemDescription = "Archaeologist’s weapon of choice. This weapon deals 8 (+ enchantment) points of damage per flick. Also hits enemies behind target. can pull enemies closer.";
		
		damage = 8+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		myBonus.add(new spearBonus());
		// TODO Auto-generated constructor stub
		name="Whip";
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Whip";
	}
	*/
}
