package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class fireballBonus extends rangedBonus {
	
	

	protected void hitEffects(Entity user, int x, int y)
	{
		explode( user, new ImOnFire(5,5) ,  x,  y, 3);
	}
	
}
