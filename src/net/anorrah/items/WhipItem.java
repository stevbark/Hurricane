package net.anorrah.items;

import net.anorrah.items.bonus.MeleeWeaponBonus;
import net.anorrah.items.bonus.spearBonus;

public class WhipItem extends MeleeWeaponItem 
{

	public WhipItem(int levelBonus) 
	{
		super(levelBonus);
		super.itemDescription = "Whip";
		
		damage = 8+enchantment;
		myBonus.add(new MeleeWeaponBonus(damage));
		myBonus.add(new spearBonus());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Whip";
	}
	
}
