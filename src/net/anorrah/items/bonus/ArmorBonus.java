package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class ArmorBonus extends bonus{

	int reduction=2;
	
	public ArmorBonus( int reduction)
	{
		this.reduction+=reduction;
	}
	
	public void onBeenHit(Entity user,Entity enemy,damageObject damage)
	{
		damage.damage -= reduction;
		System.out.println("hit for " + damage.damage + " damage");
	}
}
