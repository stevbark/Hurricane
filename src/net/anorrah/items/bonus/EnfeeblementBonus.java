package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class EnfeeblementBonus extends bonus{

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		
		if( ((int) (Math.random()%2) )==0)
		{
			enemy.addToList(new feebleBonus());
		}
		
	}
	
}
