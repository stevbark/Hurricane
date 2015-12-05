package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class GABonus extends bonus {

	public void onDeath(Entity user)
	{
		user.heal(user.getHealth());
	}
}
