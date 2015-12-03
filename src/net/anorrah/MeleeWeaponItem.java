package net.anorrah;

import java.util.ArrayList;

public abstract class MeleeWeaponItem extends ItemObject{

	int damage;
	
	
	public MeleeWeaponItem(int levelBonus)
	{
		super(levelBonus);
		possibleBonuses = new ArrayList<bonus>();
	}

	@Override
	public abstract String description();

//	public damageObject attack()
//	{
//	//	return new damageObject(damage, null);
//	}

	
}
