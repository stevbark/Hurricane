package net.anorrah;

import java.util.ArrayList;

public class RangedWeaponItem extends ItemObject{
	
	double damage;

	public RangedWeaponItem(int levelBonus)
	{
		super(levelBonus);
		possibleBonuses = new ArrayList<bonus>();
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	public double attack()
	{
		return damage;
	}
	
	
}
