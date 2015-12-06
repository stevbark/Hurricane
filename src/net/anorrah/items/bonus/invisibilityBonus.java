package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class invisibilityBonus extends bonus {

	public invisibilityBonus(int duration)
	{
		isTemp= true;
		turnsLeft = duration;
	}
	public void invisible(Entity user)
	{
		user.becomeInvisible();
	}
}
