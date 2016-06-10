package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.items.bonus.bonus;

public class RangedWeaponItem extends ItemObject{
	
	public double damage;

	public RangedWeaponItem(int levelBonus)
	{
		super(levelBonus);
		possibleBonuses = new ArrayList<bonus>();
		generateBonus();
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/


	
	
}
