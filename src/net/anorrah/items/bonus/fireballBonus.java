package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class fireballBonus extends rangedBonus {
	
	

	protected void hitEffects(Entity user, int x, int y)
	{
		explode( user, new blowUpBonus(10) ,  x,  y, 1);
	}
	
}
