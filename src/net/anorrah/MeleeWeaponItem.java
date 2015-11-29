package net.anorrah;

import java.util.ArrayList;

public abstract class MeleeWeaponItem extends ItemObject{

	double damage;
	
	
	public MeleeWeaponItem(int levelBonus)
	{
		super(levelBonus);
		possibleEnchantments = new ArrayList<Object>();
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
