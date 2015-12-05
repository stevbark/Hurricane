package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class fireMeleeBonus extends bonus {

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
//		
		
		enemy.addToList(new ImOnFire(new damageObject(5,damageObject.Type.fire),3) );
	}
	
}
