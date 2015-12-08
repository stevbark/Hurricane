package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class AxeBonus extends bonus {

	
	
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		if(Math.random() <.1)
		{
			System.out.println("critical hit");
			damage.damage +=10;
		}
	}

}
