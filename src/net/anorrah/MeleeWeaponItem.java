package net.anorrah;

import java.util.ArrayList;

public abstract class MeleeWeaponItem extends ItemObject{

	
	
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

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateBonus() {
		// TODO Auto-generated method stub
		
	}
}
