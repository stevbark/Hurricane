package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class GABonus extends bonus {

	boolean canBeUsed = true;
	public void onDeath(Entity user)
	{
		if(canBeUsed&&user.health<=0)
		{
			user.heal(user.getMaxHealth());
			canBeUsed=false;
			System.out.println("You were saved by your armor!");
		}
	}
}
