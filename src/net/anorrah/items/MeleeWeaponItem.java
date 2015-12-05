package net.anorrah.items;

import java.awt.Graphics;
import java.util.ArrayList;

import net.anorrah.items.bonus.bonus;

public abstract class MeleeWeaponItem extends ItemObject{

	int damage;
	public int[] id;
	
	public MeleeWeaponItem(int levelBonus)  
	{
		super(levelBonus);
		possibleBonuses = new ArrayList<bonus>();
	}
	
	public void render(Graphics g)
	{
		
	}

	@Override
	public abstract String description();

//	public damageObject attack()
//	{
//	//	return new damageObject(damage, null);
//	}

	
}
