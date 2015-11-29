package net.anorrah;

import java.util.ArrayList;

public class ArmorItem extends ItemObject {

	double defence;
	bonuses bon = new knockback();
	public ArmorItem(int currentLevel) {
		super(currentLevel);
		possibleEnchantments = new ArrayList<Object>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void effect(enemyEntities enemy,damageObject damage)
	{
		
		TeleportBonus x = new TeleportBonus();
		x.effect(enemy,damage);
		System.out.println("hit for " + damage.damage + " damage");
	}

	

	
	
	
	

}
