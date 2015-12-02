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
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	public damageObject attack()
	{
		return new damageObject(damage, null);
	}

	
}
