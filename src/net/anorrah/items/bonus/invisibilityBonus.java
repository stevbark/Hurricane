package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class invisibilityBonus extends bonus {

	public void invisible(Entity user)
	{
		user.becomeInvisible();
	}
}
